/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package leetcode.solutions;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 */
public class MergeTwoSortedList {
    public ListNode<Integer> mergeTwoLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> dummy = new ListNode<Integer>(); //simplifies bootstrap!
        ListNode<Integer> curHead = dummy;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                curHead.next = list2;
                break;
            } else if (list2 == null) {
                curHead.next = list1;
                break;
            } else if (list1.val < list2.val) { //move only list1 (e.g. l1=[1,2,4], l2=[5], we advance list1 until we "match" with l2)
                curHead.next = list1;
                list1 = list1.next;
            } else { //list1.val >= list2.val - move only list2
                curHead.next = list2;
                list2 = list2.next;
            }
            curHead = curHead.next;
        }
        return dummy.next;
    }
}