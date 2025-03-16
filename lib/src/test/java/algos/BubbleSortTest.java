package algos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

import leetcode.solutions.Permutations;

public class BubbleSortTest {

    @Test
    public void testSortEmptyArray() {
        int[] input = {};
        int[] expected = {};
        BubbleSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortSingleElementArray() {
        int[] input = { 1 };
        int[] expected = { 1 };
        BubbleSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortAlreadySortedArray() {
        int[] input = { 1, 2, 3, 4, 5 };
        int[] expected = { 1, 2, 3, 4, 5 };
        BubbleSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortUnsortedArray() {
        int[] input = { 5, 3, 1, 4, 2 };
        int[] expected = { 1, 2, 3, 4, 5 };
        BubbleSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testSortArrayWithDuplicates() {
        int[] input = { 4, 2, 2, 3, 1 };
        int[] expected = { 1, 2, 2, 3, 4 };
        BubbleSort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testShuffles() {
        int n = 10;
        int[] expectedArr = new int[n];
        for (int i = 1; i <= n; i++) {
            expectedArr[i - 1] = i;
        }

        int[] arr = Arrays.copyOf(expectedArr, n);
        int numShuffles = 10000;
        for (int i = 0; i < numShuffles; i++) {
            shuffle(arr);
            BubbleSort.sort(arr);
            assertArrayEquals(expectedArr, arr);
        }
    }

    @Test
    public void testAllPermutations() {
        int n = 10;
        int[] expectedArr = new int[n];
        for (int i = 1; i <= n; i++) {
            expectedArr[i - 1] = i;
        }

        int[] arr = Arrays.copyOf(expectedArr, n);
        var permutations = new Permutations().permuteRecursively(arr);
        for (var permutation : permutations) {
            BubbleSort.sort(permutation);
            assertArrayEquals(expectedArr, permutation);
        }
    }

    private void shuffle(int[] arr) {
        var rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            var j = rnd.nextInt(arr.length);
            var tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}