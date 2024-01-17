package leetcode.solutions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexOfSubstringSimpleTest {

    @Test
    void test1() {
        int actual = IndexOfSubstringSimple.strStr("mississippi", "issip");
        assertEquals(4, actual);
    }
}