package leetcode.solutions;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RemoveArrayElementInPlaceTest {

    @Test
    void test1() {
        int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
        var countNotEqualToVal = RemoveArrayElementInPlace.removeElement(arr, 2);
        assertEquals(countNotEqualToVal, 5);
        assertArrayEquals(Arrays.copyOf(arr, 5), new int[]{0, 1, 3, 0, 4});
    }
}