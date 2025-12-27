package algorithm.design;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanner {
    private final Deque<int[]> deque;
    private int day = 1;
    public StockSpanner() {
        deque = new ArrayDeque<>();
    }

    public int next(int price) {
        while ((!deque.isEmpty())) {
            if (deque.peekLast()[1] <= price) {
                deque.pollLast();
            } else {
                break;
            }
        }
        int span = (deque.isEmpty() ? day : day - deque.peekLast()[0]);
        deque.addLast(new int[]{day, price});
        ++day;
        return span;
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(75));
        System.out.println(stockSpanner.next(85));
    }
}
