package algorithm.array;

import java.util.ArrayDeque;
import java.util.Queue;

public class JumpGame2 {

	// double queue approach
	public int jump(int[] nums) {
		Queue<Integer> q1 = new ArrayDeque<>();
		Queue<Integer> q2 = new ArrayDeque<>();
		boolean[] reached = new boolean[nums.length];
		int minJump = 0;
		q1.add(0);
		reached[0] = true;
		while (!reached[nums.length - 1] && (!q1.isEmpty() || !q2.isEmpty())) {
			++minJump;
			if (q2.isEmpty()) {
				process(nums, reached, q1, q2);
			} else if (q1.isEmpty()) {
				process(nums, reached, q2, q1);
			}
		}
		return minJump;
	}

	private void process(int[] nums, boolean[] reached, Queue<Integer> pq, Queue<Integer> eq) {
		while (!reached[nums.length - 1] && !pq.isEmpty()) {
			int idx = pq.poll();
			for (int jump = nums[idx]; jump >= 0; --jump) {
				if (idx + jump >= nums.length - 1) {
					reached[nums.length - 1] = true;
					break;
				} else if (reached[idx + jump]) {
					break;
				} else if (!reached[idx + jump] && nums[idx + jump] != 0) {
					eq.add(idx + jump);
					reached[idx + jump] = true;
				}
			}
		}
	}

	// single queue approach
	public int jumpV2(int[] nums) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] reached = new boolean[nums.length];
		int minJump = 0;
		// q content => new int[] {idx, minJumpToReachIdx}
		q.add(new int[] { 0, 0 });
		reached[0] = true;
		while (!reached[nums.length - 1] && !q.isEmpty()) {
			int[] loc = q.poll();
			for (int jump = nums[loc[0]]; jump >= 1; --jump) {
				if (loc[0] + jump >= nums.length - 1) {
					reached[nums.length - 1] = true;
					minJump = loc[1] + 1;
					break;
				} else if (reached[loc[0] + jump]) {
					break;
				} else if (nums[loc[0] + jump] != 0) {
					q.add(new int[] { loc[0] + jump, loc[1] + 1 });
					reached[loc[0] + jump] = true;
				}
			}
		}
		return minJump;
	}

	// recursive-approach
	public int jumpV3(int[] nums) {
		int[] reached = new int[nums.length];
		if (nums.length == 1) {
			return 0;
		} else {
			dfs(nums, 0, reached, 0);
			return reached[nums.length - 1];
		}
	}

	private void dfs(int[] nums, int idx, int[] reached, int jumps) {
		for (int jump = nums[idx]; jump >= 1; --jump) {
			if (idx + jump >= nums.length - 1) {
				if (reached[nums.length - 1] == 0 || jumps + 1 < reached[nums.length - 1]) {
					reached[nums.length - 1] = jumps + 1;
				}
				break;
			} else if ((reached[idx + jump] == 0 || reached[idx + jump] > jumps + 1) && nums[idx + jump] != 0) {
				reached[idx + jump] = jumps + 1;
				dfs(nums, idx + jump, reached, jumps + 1);
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 1, 1, 1 };
		int minJump = new JumpGame2().jumpV3(nums);
		System.out.println(minJump);
	}
}
