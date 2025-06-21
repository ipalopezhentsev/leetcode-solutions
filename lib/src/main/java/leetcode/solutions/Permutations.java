package leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #46
 * https://leetcode.com/problems/permutations/description/
 * 
 * idea is here:
 * https://ericlippert.com/2013/04/15/producing-permutations-part-one/
 * and here is non-recursive version:
 * https://www.techiedelight.com/generate-permutations-string-java-recursive-iterative/
 * I.e. if we have all permutations of size 2:
 * 1,2
 * 2,1
 * and we would like to then incorporate next element 3 from nums, we remove
 * these permutations of size 2 and replace them with the element 3 inserted in
 * every position of each source permutation:
 * 3,1,2
 * 1,3,2
 * 1,2,3
 * 3,2,1
 * 2,3,1
 * 2,1,3
 * 
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        final List<List<Integer>> res = new ArrayList<>();
        // we start partial permutations with the lowest dimension, empty set and
        // will be adding to it new and new numbers from nums.
        res.add(new ArrayList<>());
        for (int curNum : nums) {
            var partialPermIter = res.listIterator();
            while (partialPermIter.hasNext()) {
                List<Integer> partialPermutation = partialPermIter.next();
                partialPermIter.remove();
                // now incorporate curNum into all positions of partial
                // permutation and add then prior to cursor
                for (int i = 0; i < partialPermutation.size() + 1; i++) {
                    List<Integer> increasedPermutation = new ArrayList<>();
                    for (int j = 0; j < i; j++) {
                        increasedPermutation.add(partialPermutation.get(j));
                    }
                    increasedPermutation.add(curNum);
                    for (int j = i; j < partialPermutation.size(); j++) {
                        increasedPermutation.add(partialPermutation.get(j));
                    }
                    partialPermIter.add(increasedPermutation);
                }
            }
        }
        return res;
    }

    public List<int[]> permuteRecursively(int[] nums) {
        List<int[]> initPartialPerms = new ArrayList<>();
        initPartialPerms.add(new int[] {});
        return permuteRec(nums, initPartialPerms);
    }

    private List<int[]> permuteRec(int[] nums, List<int[]> partialPermutations) {
        if (nums.length == 0) {
            // partialPermutations now has permutations of full scope of elems, notify
            return partialPermutations;
        }
        int curNum = nums[0];
        var newNums = Arrays.copyOfRange(nums, 1, nums.length);
        List<int[]> accumPermutations = new ArrayList<>();
        // pass to lower recursion level smaller nums collection but larger
        // partialPermutations, enriched by inserting curNum in all positions of current
        // partialPermutations
        var iter = partialPermutations.listIterator();
        while (iter.hasNext()) {
            int[] curPermutation = iter.next();
            // insert curNum into all positions in curPermutation, producing more
            // permutations
            iter.remove();
            for (int j = 0; j < curPermutation.length + 1; j++) {
                int[] higherLevelPermutation = new int[curPermutation.length + 1];
                for (int k = 0; k < j; k++) {
                    higherLevelPermutation[k] = curPermutation[k];
                }
                higherLevelPermutation[j] = curNum;
                for (int k = j + 1; k < higherLevelPermutation.length; k++) {
                    higherLevelPermutation[k] = curPermutation[k - 1];
                }
                iter.add(higherLevelPermutation);
            }
        }
        List<int[]> higherLevelPermutations = permuteRec(newNums, partialPermutations);
        accumPermutations.addAll(higherLevelPermutations);
        return accumPermutations;
    }
}
