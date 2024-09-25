package algorithm.linkedlist;

import java.util.Objects;

public class ReorderList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    public void reorderList(ListNode head) {
        if (Objects.isNull(head)) {
            return;
        }
        ListNode slowPointer = head;
        ListNode fastPointer = Objects.nonNull(slowPointer.next) ? slowPointer.next : slowPointer;
        if (Objects.equals(slowPointer, fastPointer)) {
            return;
        }
        while (Objects.nonNull(fastPointer) && Objects.nonNull(fastPointer.next)) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        ListNode secondListHead = Objects.nonNull(slowPointer.next) ? slowPointer.next : slowPointer;
        slowPointer.next = null;
        ListNode reversedList = null;
        while(Objects.nonNull(secondListHead)) {
            ListNode temp = secondListHead;
            secondListHead = secondListHead.next;
            temp.next = reversedList;
            reversedList = temp;
        }
        ListNode tempHead = head;
        while(Objects.nonNull(reversedList)) {
            ListNode temp = reversedList;
            reversedList = reversedList.next;
            temp.next = head.next;
            head.next = temp;
            head = head.next.next;
        }
        head = tempHead;
    }

    public static void main(String[] args) {
        int[] nums = {};
        ListNode head = convertToList(nums);
        new ReorderList().reorderList(head);
        printList(head);
    }

    private static ListNode convertToList(int[] nums) {
        ListNode head = null;
        if (nums != null && nums.length > 0) {
            int idx = 0;
            head = new ListNode(nums[idx++]);
            ListNode temp = head;
            for (; idx <= nums.length - 1; ++idx) {
                temp.next = new ListNode(nums[idx]);
                temp = temp.next;
            }
        }
        return head;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("null");
    }
}
