package leetcode.solutions;

import java.util.List;

public class ListUtils {
    public static <V> ListNode<V> toList(List<V> srcList) {
        if (srcList == null) {
            return null;
        }
        ListNode<V> res = new ListNode<>();
        ListNode<V> head = res;
        for (V v : srcList) {
            head.next = new ListNode<>(v);
            head = head.next;
        }
        return res.next;
    }

    public static <V> boolean listsEqual(ListNode<V> list1, ListNode<V> list2) {
        if (list1 == null && list2 != null || list1 != null && list2 == null) {
            return false;
        }
        if (list1 == list2) {
            return true;
        }
        while (list1 != null && list2 != null) {
            if (list1.val != list2.val) {
                return false;
            }
            list1 = list1.next;
            list2 = list2.next;
        }
        return list1 == null && list2 == null;
    }
}
