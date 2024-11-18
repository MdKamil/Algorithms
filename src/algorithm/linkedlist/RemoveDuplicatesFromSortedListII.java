package algorithm.linkedlist;

import java.util.Objects;

public class RemoveDuplicatesFromSortedListII {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int x) {
            val = x;
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

    public ListNode deleteDuplicates(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode prevNode = dummyNode;
        int previousVal = head.val + 1;
        while (Objects.nonNull(head)) {
            if (Objects.equals(head.val, previousVal) || (Objects.nonNull(head.next) && Objects.equals(head.val, head.next.val))) {
                prevNode.next = head.next;
            } else {
                prevNode.next = head;
                prevNode = head;
            }
            previousVal = head.val;
            head = head.next;
        }
        return dummyNode.next;
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

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,2};
        ListNode head = convertToList(nums);
        head = new RemoveDuplicatesFromSortedListII().deleteDuplicates(head);
        printList(head);
    }
}
