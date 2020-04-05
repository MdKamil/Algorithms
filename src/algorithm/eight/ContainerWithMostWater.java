package algorithm.eight;

public class ContainerWithMostWater {
	public static int maxArea(int[] height) {
		int maxArea = 0;
		if (height != null) {
			int front = 0;
			int back = height.length - 1;
			while (front < back) {
				int length = back - front;
				int minheight = Math.min(height[front], height[back]);
				maxArea = Math.max(maxArea, minheight * length);
				if (height[front] > height[back]) {
					--back;
				} else if (height[front] < height[back]) {
					++front;
				} else {
					++front;
					--back;
				}
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		int maxArea = maxArea(height);
		System.out.println(maxArea);
	}
}
