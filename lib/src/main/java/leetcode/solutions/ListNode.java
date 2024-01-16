package leetcode.solutions;

public class ListNode<V> {
    V val;
    ListNode<V> next;

    ListNode() {
    }

    ListNode(V val) {
        this.val = val;
    }

    ListNode(V val, ListNode<V> next) {
        this.val = val;
        this.next = next;
    }
}