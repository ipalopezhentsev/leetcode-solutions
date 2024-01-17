package leetcode.solutions;

/**
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/
 * solution via iterations, O(n*m), n - len haystack, m - len needle
 */
public class IndexOfSubstringSimple {
    public static int strStr(String haystack, String needle) {
        for (int i = 0; i < haystack.length(); i++) {
            if (i + needle.length() > haystack.length()) {
                break;
            }
            if (matches(haystack, needle, i)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean matches(String haystack, String needle, int idxStart) {
        for (int i=0; i<needle.length(); i++) {
            if (haystack.charAt(idxStart + i) != needle.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
