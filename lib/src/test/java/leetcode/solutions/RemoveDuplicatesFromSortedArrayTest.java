package leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RemoveDuplicatesFromSortedArrayTest {
    @Test
    public void test1() {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        var countUnique = RemoveDuplicatesFromSortedArray.removeDuplicates(arr);
        assertEquals(countUnique, 5);
        assertArrayEquals(Arrays.copyOf(arr, 5), new int[]{0, 1, 2, 3, 4});
    }
}