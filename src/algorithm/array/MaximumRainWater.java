package algorithm.array;

public class MaximumRainWater {

	public static int maxWaterTrapped(int[] height) {
		int[] leftMax = new int[height.length];
		int[] rightMax = new int[height.length];
		int currMax = height[0];
		leftMax[0] = currMax;
		for (int i = 1; i <= height.length - 1; ++i) {
			if (height[i] >= currMax) {
				currMax = height[i];
				leftMax[i] = currMax;
			} else {
				leftMax[i] = currMax;
			}
		}
		currMax = height[height.length - 1];
		rightMax[0] = currMax;
		for (int i = height.length - 2; i >= 0; --i) {
			if (height[i] >= currMax) {
				currMax = height[i];
				rightMax[i] = currMax;
			} else {
				rightMax[i] = currMax;
			}
		}
		int maxTrapped = 0;
		for (int i = 0; i <= height.length - 1; ++i) {
			int val = Math.min(leftMax[i], rightMax[i]) - height[i];
			if (val >= 1) {
				maxTrapped += val;
			}
		}
		return maxTrapped;
	}

	public static int maxWaterTrapped2(int[] height) {
		int[] leftMax = new int[height.length];
		int[] rightMax = new int[height.length];
		int front = 1;
		int rear = height.length - 2;
		int frontMax = height[0];
		int rearMax = height[height.length - 1];
		for (int maxRun = 1; maxRun <= height.length - 1; ++maxRun) {
			if (height[front] >= frontMax) {
				frontMax = height[front];
				leftMax[front] = frontMax;
			} else {
				leftMax[front] = frontMax;
			}

			if (height[rear] >= rearMax) {
				rearMax = height[rear];
				rightMax[rear] = rearMax;
			} else {
				rightMax[rear] = rearMax;
			}
			++front;
			--rear;
		}
		int maxTrapped = 0;
		for (int i = 0; i <= height.length - 1; ++i) {
			int val = Math.min(leftMax[i], rightMax[i]) - height[i];
			if (val >= 1) {
				maxTrapped += val;
			}
		}
		return maxTrapped;
	}

	public static void main(String[] args) {
		// System.out.println(maxWaterTrapped(new int[] { 0, 1, 0, 2, 1, 0, 1,
		// 3, 2, 1, 2, 1 }));
		System.out.println(maxWaterTrapped(new int[] { 3, 0, 0, 2, 0, 4 }));
	}

}
