/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package leetcode.solutions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static leetcode.solutions.ListUtils.toList;
import static leetcode.solutions.ListUtils.listsEqual;

import static org.junit.jupiter.api.Assertions.*;

class MergeTwoSortedListTest {
    private MergeTwoSortedList subj;

    @BeforeEach
    void init() {
        subj = new MergeTwoSortedList();
    }

    @Test
    void test1() {
        var res = subj.mergeTwoLists(toList(List.of(5)), toList(List.of(1, 2, 4)));
        assertTrue(listsEqual(res, toList(List.of(1, 2, 4, 5))));
    }
}
