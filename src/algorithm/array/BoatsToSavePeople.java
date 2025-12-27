package algorithm.array;

import java.util.*;

public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        int minNoOfBoats = 0;
        Arrays.sort(people);
        Deque<Integer> deque = new ArrayDeque<>();
        for (int weight : people) {
            deque.add(weight);
        }
        for (int idx = people.length - 1; idx >= 0 && !deque.isEmpty(); --idx) {
            if (people[idx] == limit) {
                deque.pollLast();
            } else {
                int weight1 = deque.pollLast();
                if (!deque.isEmpty() && weight1 + deque.peekFirst() <= limit) {
                    deque.pollFirst();
                }
            }
            ++minNoOfBoats;
        }
        return minNoOfBoats;
    }

    public static void main(String[] args) {
        int[] weight = {3,2,3,2,2};
        int limit = 6;
        BoatsToSavePeople boatsToSavePeople = new BoatsToSavePeople();
        int minNoOfBoats = boatsToSavePeople.numRescueBoats(weight, limit);
        System.out.println(minNoOfBoats);
    }
}
