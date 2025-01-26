package algorithm.linkedlist;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SortedListToBST {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        @Override
        public String toString() {
            return Integer.toString(val);
        }

    }

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

    public TreeNode sortedListToBST(ListNode head) {
        Map<Integer, ListNode> listNodeIdxMap = new HashMap<>();
        int length = 0;
        while (Objects.nonNull(head)) {
            listNodeIdxMap.put(++length, head);
            head = head.next;
        }
        return Objects.equals(length, 0) ? null : formTree(1, length, listNodeIdxMap);
    }

    private TreeNode formTree(int left, int right, Map<Integer, ListNode> listNodeIdxMap) {
        if (left > right) {
            return null;
        }
        int mid = ((right - left) + 1) % 2 == 0 ? ((right - left) + 1) / 2 : (((right - left) + 1) / 2) + 1 ;
        TreeNode root = new TreeNode(listNodeIdxMap.get(mid).val);
        root.left = formTree(left, mid - 1, listNodeIdxMap);
        root.right = formTree(mid + 1, right, listNodeIdxMap);
        return root;
    }
}
