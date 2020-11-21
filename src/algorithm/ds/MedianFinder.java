package algorithm.ds;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

public class MedianFinder {

	private PriorityQueue<Integer> maxHeap;
	private PriorityQueue<Integer> minHeap;
	private Comparator<Integer> maxComparator;

	public MedianFinder() {
		maxComparator = Collections.reverseOrder();
		maxHeap = new PriorityQueue<>(maxComparator);
		minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
    	if(minHeap.isEmpty()) {
    		minHeap.add(num);
    	} else if(maxHeap.isEmpty()) {
    		if(num < minHeap.peek()) {
    			maxHeap.add(num);
    		} else {
    			maxHeap.add(minHeap.poll());
    			minHeap.add(num);
    		}
    	} else {
    		if(num <= maxHeap.peek()) {
    			maxHeap.add(num);
    		} else {
    			minHeap.add(num);
    		}

    		if(Math.abs(maxHeap.size() - minHeap.size()) == 2) {
    			if(maxHeap.size() > minHeap.size()) {
    				minHeap.add(maxHeap.poll());
    			} else {
    				maxHeap.add(minHeap.poll());
    			}
    		}
    	}
    }
    
    public double findMedian() {
        if(maxHeap.size() == minHeap.size()) {
        	int a = maxHeap.peek();
        	int b = minHeap.peek();
        	float c = ((float)a+b)/2;
        	return c;
        } else {
        	if(maxHeap.size() > minHeap.size()) {
        		return maxHeap.peek();
        	} else {
        		return minHeap.peek();
        	}
        }
    }

}