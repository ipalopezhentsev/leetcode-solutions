package leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * #46
 * https://leetcode.com/problems/lru-cache/description/
 */
public class LRUCache {
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }
    private final Map<Integer, Node> map;
    private final Node head, tail;
    private final int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1;
        }
        moveToHead(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        Node n = map.get(key);
        if (n != null) {
            moveToHead(n);
            n.value = value;
            return;
        }
        if (map.size() == capacity) {
            evictLru();
        }
        n = new Node();
        n.key = key;
        n.value = value;
        n.prev = head;
        n.next = head.next;
        head.next.prev = n;
        head.next = n;
        map.put(key, n);
    }

    private void moveToHead(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    private void evictLru() {
        Node lru = tail.prev;
        if (lru == head) {
            return;
        }
        lru.prev.next = tail;
        tail.prev = lru.prev;
        map.remove(lru.key);
    }
}
