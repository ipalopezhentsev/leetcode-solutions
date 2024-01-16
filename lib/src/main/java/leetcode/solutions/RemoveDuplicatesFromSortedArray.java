package leetcode.solutions;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 */
public class RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        int idxSlow = 0;
        int prev = nums[0];
        int countUnique = 1;
        for (int idxFast = 1; idxFast < nums.length; idxFast++) {
            int cur = nums[idxFast];
            if (cur != prev) {
                nums[++idxSlow] = cur;
                countUnique++;
            }
            prev = cur;
        }
        return countUnique;
    }
}
