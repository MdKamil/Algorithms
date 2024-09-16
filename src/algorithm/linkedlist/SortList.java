package algorithm.linkedlist;

import java.util.Objects;

// https://leetcode.com/problems/sort-list
public class SortList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    public ListNode sortList(ListNode head) {
        head = splitList(head);
        return head;
    }

    private ListNode splitList(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode slowPointer = head;
        ListNode fastPointer = Objects.nonNull(slowPointer.next) ? slowPointer.next : slowPointer;
        if (Objects.equals(slowPointer, fastPointer)) {
            return slowPointer;
        }
        while (Objects.nonNull(fastPointer) && Objects.nonNull(fastPointer.next)) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        ListNode secondListHead = Objects.nonNull(slowPointer.next) ? slowPointer.next : slowPointer;
        slowPointer.next = null;
        ListNode firstListHead = splitList(head);
        secondListHead = splitList(secondListHead);
        ListNode tempFirstListHead;
        if (secondListHead.val < firstListHead.val) {
            tempFirstListHead = firstListHead;
            firstListHead = secondListHead;
            secondListHead = tempFirstListHead;
        }
        tempFirstListHead = firstListHead;
        ListNode firstListPrevNode = firstListHead;
        while (Objects.nonNull(firstListHead) && Objects.nonNull(secondListHead)) {
            if (firstListHead.val <= secondListHead.val) {
                firstListPrevNode = firstListHead;
                firstListHead = firstListHead.next;
            } else {
                ListNode secondListCurrNode = secondListHead;
                secondListHead = secondListHead.next;
                secondListCurrNode.next = firstListHead;
                firstListPrevNode.next = secondListCurrNode;
                firstListPrevNode = secondListCurrNode;
            }
        }
        if (Objects.nonNull(secondListHead)) {
            firstListPrevNode.next = secondListHead;
        }
        firstListHead = tempFirstListHead;
        return firstListHead;
    }

    public static void main(String[] args) {
        int[] nums = {};
        ListNode head = convertToList(nums);
        head = new SortList().sortList(head);
        while (Objects.nonNull(head)) {
            System.out.println(head.val);
            head = head.next;
        }
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
}