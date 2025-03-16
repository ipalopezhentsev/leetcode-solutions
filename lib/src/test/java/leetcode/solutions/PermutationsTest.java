package leetcode.solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class PermutationsTest {

    @Test
    public void testPermuteEmptyArray() {
        Permutations permutations = new Permutations();
        int[] nums = {};
        List<List<Integer>> result = permutations.permute(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testPermuteSingleElement() {
        Permutations permutations = new Permutations();
        int[] nums = {1};
        List<List<Integer>> result = permutations.permute(nums);
        assertEquals(1, result.size());
        assertEquals(List.of(1), result.get(0));
    }

    @Test
    public void testPermuteTwoElements() {
        Permutations permutations = new Permutations();
        int[] nums = {1, 2};
        List<List<Integer>> result = permutations.permute(nums);
        assertEquals(2, result.size());
        assertTrue(result.contains(List.of(1, 2)));
        assertTrue(result.contains(List.of(2, 1)));
    }

    @Test
    public void testPermuteThreeElements() {
        Permutations permutations = new Permutations();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permutations.permute(nums);
        assertEquals(6, result.size());
        assertTrue(result.contains(List.of(1, 2, 3)));
        assertTrue(result.contains(List.of(1, 3, 2)));
        assertTrue(result.contains(List.of(2, 1, 3)));
        assertTrue(result.contains(List.of(2, 3, 1)));
        assertTrue(result.contains(List.of(3, 1, 2)));
        assertTrue(result.contains(List.of(3, 2, 1)));
    }

    @Test
    public void testPermuteRecThreeElements() {
        Permutations permutations = new Permutations();
        int[] nums = {1, 2, 3};
        List<int[]> result = permutations.permuteRecursively(nums);
        assertEquals(6, result.size());
        assertArrayEquals(new int[] {3, 2, 1}, result.get(0));
        assertArrayEquals(new int[] {2, 3, 1}, result.get(1));
        assertArrayEquals(new int[] {2, 1, 3}, result.get(2));
        assertArrayEquals(new int[] {3, 1, 2}, result.get(3));
        assertArrayEquals(new int[] {1, 3, 2}, result.get(4));
        assertArrayEquals(new int[] {1, 2, 3}, result.get(5));
    }
}