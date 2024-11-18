package algorithm.linkedlist;

import java.util.Objects;

public class PartitionList {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int x) { val = x; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    // convert array to LinkedList
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

    // print a LinkedList
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("null");
    }

    public ListNode partition(ListNode head, int x) {
        ListNode partitionHead = null;
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode prevNode = dummyNode;
        ListNode nodeToInsertAfter = dummyNode;
        while (Objects.nonNull(head)) {
            if (head.val >= x) {
                if (Objects.isNull(partitionHead)) {
                    partitionHead = head;
                    nodeToInsertAfter.next = partitionHead;
                }
                prevNode = head;
                head = head.next;
            } else {
                if (Objects.nonNull(partitionHead)) {
                    prevNode.next = head.next;
                    nodeToInsertAfter.next = head;
                    head.next = partitionHead;
                    nodeToInsertAfter = head;
                    head = prevNode.next;
                } else {
                    prevNode = head;
                    nodeToInsertAfter = head;
                    head = head.next;
                }
            }
        }
        return dummyNode.next;
    }


    public static void main(String[] args) {
        int[] nums = {10,9,8,7,6,5,4,3,2,1};
        ListNode head = convertToList(nums);
        int x = 10;
        head = new PartitionList().partition(head, x);
        printList(head);
    }
}
