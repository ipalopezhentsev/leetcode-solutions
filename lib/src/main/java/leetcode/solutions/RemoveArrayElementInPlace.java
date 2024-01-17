package leetcode.solutions;

/**
 * https://leetcode.com/problems/remove-element/description/
 */
public class RemoveArrayElementInPlace {
    public static int removeElement(int[] nums, int val) {
        int idxSlow = 0;
        for (int idxFast = 0; idxFast < nums.length; idxFast++) {
            int cur = nums[idxFast];
            if (cur != val) {
                nums[idxSlow++] = cur;
            }
        }
        return idxSlow;
    }

}
