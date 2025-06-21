package leetcode.solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LFUCacheTest {

    @Test
    public void testBasicPutAndGet() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        assertEquals(2, cache.get(2));
    }

    @Test
    public void testEvictionOrder() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1); // freq of 1 becomes 2
        cache.put(3, 3); // evicts key 2 (freq 1)
        assertEquals(1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testEvictionWithSameFrequency() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3); // both 1 and 2 have freq 1, evict 1 (oldest)
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    public void testUpdateValue() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10); // update value
        assertEquals(10, cache.get(1));
        assertEquals(2, cache.get(2));
    }

    @Test
    public void testZeroCapacity() {
        LFUCache cache = new LFUCache(0);
        cache.put(1, 1);
        assertEquals(-1, cache.get(1));
    }

    @Test
    public void testMultipleEvictions() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1); // freq 1:2, freq 2:1
        cache.put(3, 3); // evict 2
        cache.get(3); // freq 3:1
        cache.put(4, 4); // evict 1
        assertEquals(-1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testIncreaseFrequency() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.put(3, 3); // evict 2 (freq 2:1, freq 1:2)
        assertEquals(1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
    }
}