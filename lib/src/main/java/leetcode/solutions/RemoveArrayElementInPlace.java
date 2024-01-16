package leetcode.solutions;

/**
 * https://leetcode.com/problems/remove-element/description/
 */
public class RemoveArrayElementInPlace {
    public static int removeElement(int[] nums, int val) {
        int countNotEqualToVal = 0;
        int idxSlow = 0;
        for (int idxFast = 0; idxFast < nums.length; idxFast++) {
            int cur = nums[idxFast];
            if (cur != val) {
                countNotEqualToVal++;
                nums[idxSlow++] = cur;
            }
        }
        return countNotEqualToVal;
    }

}
