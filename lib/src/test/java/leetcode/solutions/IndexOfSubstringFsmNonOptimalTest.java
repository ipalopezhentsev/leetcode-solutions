package leetcode.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexOfSubstringFsmNonOptimalTest {
    IndexOfSubstringFsmNonOptimal subj = new IndexOfSubstringFsmNonOptimal();

    @Test
    void test1() {
        int actual = subj.strStr("mississippi", "issip");
        assertEquals(4, actual);
    }
}