package algorithm.linkedlist;

public class InsertionSortList {
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

    public ListNode insertionSortList(ListNode head) {
        ListNode sortedListHead;
        sortedListHead = head;
        head = head.next;
        sortedListHead.next = null;
        ListNode dummy = new ListNode();
        while(head != null) {
            ListNode curr = head;
            head = head.next;
            curr.next = null;
            dummy.next = sortedListHead;
            ListNode sortedListCurrNode = dummy.next;
            ListNode prev = dummy;
            while(true) {
                if (curr.val >= sortedListCurrNode.val) {
                    if (sortedListCurrNode.next == null) {
                        sortedListCurrNode.next = curr;
                        break;
                    }
                    prev = sortedListCurrNode;
                    sortedListCurrNode = sortedListCurrNode.next;
                } else {
                    prev.next = curr;
                    curr.next = sortedListCurrNode;
                    break;
                }
            }
            sortedListHead = dummy.next;
        }
        return sortedListHead;
    }

    public static void main(String[] args) {
        int[] nodes = {9,8,7,3,2,8};
        ListNode head = convertToList(nodes);
        InsertionSortList sortList = new InsertionSortList();
        ListNode sortedList = sortList.insertionSortList(head);
        printList(sortedList);
    }
}
