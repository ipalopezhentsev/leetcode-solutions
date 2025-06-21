package leetcode.solutions;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * #460
 * https://leetcode.com/problems/lfu-cache/description/
 */
public class LFUCache {
    private final int capacity;
    private final Map<Integer,Node> map;
    static class Node {
        int key;
        int value;
        int counter;
    }
    //values are LinkedHashSet to 1) preserve insertion order, so on eviction we can
    //remove the oldest key among other keys with the same frequency,
    //2) allow O(1) removal of the key from the bucket when moving it to higher frequency bucket.
    private final Map<Integer, LinkedHashSet<Integer>> counterToBucket;
    private int lowestFreq;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        counterToBucket = new HashMap<>();
    }
    
    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1;
        }
        moveToNextFreqBucket(n);
        return n.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return; // no capacity to store anything
        }
        Node n = map.get(key);
        if (n != null) {
            n.value = value;
            moveToNextFreqBucket(n);
            return;
        }
        if (map.size() == capacity) {
            evictLfu();
        }
        n = new Node();
        n.key = key;
        n.value = value;
        n.counter = 1;
        map.put(key, n);
        LinkedHashSet<Integer> bucket = counterToBucket.get(n.counter);
        if (bucket == null) {
            bucket = new LinkedHashSet<>();
            counterToBucket.put(n.counter, bucket);
        };
        bucket.add(n.key);
        lowestFreq = n.counter;
    }

    private void moveToNextFreqBucket(Node n) {
        LinkedHashSet<Integer> oldBucket = counterToBucket.get(n.counter);
        oldBucket.remove(n.key);
        if (oldBucket.isEmpty()) {
            counterToBucket.remove(n.counter);
            //example: we're removing bucket with counter 2 as it's empty now, but there still is bucket with counter 1,
            //it hosts the actual lfu, we cannot remove it.
            if (lowestFreq == n.counter) {
                lowestFreq++;
            }
        }
        n.counter++;
        var newBucket = counterToBucket.get(n.counter);
        if (newBucket == null) {
            newBucket = new LinkedHashSet<>();
            counterToBucket.put(n.counter, newBucket);
        }
        newBucket.add(n.key);
    }

    private void evictLfu() {
        LinkedHashSet<Integer> lfuBucket = counterToBucket.get(lowestFreq);
        int lfuKey = lfuBucket.getFirst();
        lfuBucket.remove(lfuKey);
        if (lfuBucket.isEmpty()) {
            counterToBucket.remove(lowestFreq);
            lowestFreq++;
        }
        map.remove(lfuKey);
    }
}
