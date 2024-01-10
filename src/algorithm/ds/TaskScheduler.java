package algorithm.ds;

import java.util.*;

public class TaskScheduler {

    private static class Task {
        char c;
        Integer count;
        Integer next;

        public Task(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        PriorityQueue<Task> backlogQ = new PriorityQueue<>((a, b) -> -a.count.compareTo(b.count));
        PriorityQueue<Task> execQ = new PriorityQueue<>(Comparator.comparing(a -> a.next));
        HashMap<Character, Integer> map = new HashMap<>();
        for (int idx = 0; idx <= tasks.length - 1; ++idx) {
            map.put(tasks[idx], map.getOrDefault(tasks[idx], 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            backlogQ.add(new Task(entry.getKey(), entry.getValue()));
        }
        for (int count = 1; count <= n + 1 && !backlogQ.isEmpty(); ++count) {
            Task task = backlogQ.poll();
            task.next = count;
            execQ.add(task);
        }
        int minTime = 0;
        while (!execQ.isEmpty()) {
            Task task = execQ.poll();
            minTime = task.next;
            --task.count;
            task.next = task.next + (n + 1);
            if (!backlogQ.isEmpty() && (backlogQ.peek().count > task.count || task.count == 0)) {
                Task backlogTask = backlogQ.poll();
                backlogTask.next = task.next;
                execQ.add(backlogTask);
                if (task.count != 0) {
                    backlogQ.add(task);
                }
            } else if (task.count > 0) {
                execQ.add(task);
            }
        }
        return minTime;
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'B', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 3;
        int minTime = new TaskScheduler().leastInterval(tasks, n);
        System.out.println(minTime);
    }
}
