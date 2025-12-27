package algorithm.tree;

import java.util.*;

public class LargestValueInEachTreeRow {
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

    private static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    private static class Pair<T1, T2> {
        T1 first;
        T2 second;

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        if (Objects.nonNull(root)) {
            Integer currLevel = 0;
            Integer maxValAtCurrentLevel = null;
            queue.add(new Pair<>(root, 1));
            while (!queue.isEmpty()) {
                Pair<TreeNode, Integer> currRoot = queue.poll();
                if (currRoot.second > currLevel) {
                    if (Objects.nonNull(maxValAtCurrentLevel)) {
                        list.add(maxValAtCurrentLevel);
                    }
                    currLevel = currRoot.second;
                    maxValAtCurrentLevel = currRoot.first.val;
                }
                maxValAtCurrentLevel = Math.max(maxValAtCurrentLevel, currRoot.first.val);
                if (Objects.nonNull(currRoot.first.left)) {
                    queue.add(new Pair<>(currRoot.first.left, currLevel + 1));
                }
                if (Objects.nonNull(currRoot.first.right)) {
                    queue.add(new Pair<>(currRoot.first.right, currLevel + 1));
                }
            }
            list.add(maxValAtCurrentLevel);
        }
        return list;
    }

    public static void main(String[] args) {
        String s = "[]";
        TreeNode root = stringToTreeNode(s);
        LargestValueInEachTreeRow largestValueInEachTreeRow = new LargestValueInEachTreeRow();
        List<Integer> list = largestValueInEachTreeRow.largestValues(root);
        System.out.println(list);
    }
}
