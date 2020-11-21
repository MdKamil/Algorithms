package algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsecutiveSequence {

	public static int longestConsecutiveV1(int[] nums) {
		int maxConsecutiveSequenceLength = 0;
		if (nums != null && nums.length > 0) {
			int min = nums[0];
			int max = nums[0];
			Set<Integer> set = new HashSet<>();
			set.add(nums[0]);
			for (int idx = 1; idx <= nums.length - 1; ++idx) {
				if (nums[idx] < min) {
					min = nums[idx];
				}
				if (nums[idx] > max) {
					max = nums[idx];
				}
				set.add(nums[idx]);
			}
			int currLength = 0;
			for (int from = min; from <= max; ++from) {
				if (set.contains(from)) {
					++currLength;
				} else {
					maxConsecutiveSequenceLength = Math.max(maxConsecutiveSequenceLength, currLength);
					currLength = 0;
				}
			}
			maxConsecutiveSequenceLength = Math.max(maxConsecutiveSequenceLength, currLength);
		}
		return maxConsecutiveSequenceLength;
	}

	public static int longestConsecutiveV2(int[] nums) {
		int maxConsecutiveSequenceLength = 0;
		if (nums != null && nums.length > 0) {
			Arrays.sort(nums);
			int currLength = 1;
			for (int idx = 1; idx <= nums.length - 1; ++idx) {
				if (nums[idx] - nums[idx - 1] == 1) {
					++currLength;
				} else if (nums[idx] - nums[idx - 1] == 0) {
					// no-op
				} else {
					maxConsecutiveSequenceLength = Math.max(maxConsecutiveSequenceLength, currLength);
					currLength = 1;
				}
			}
			maxConsecutiveSequenceLength = Math.max(maxConsecutiveSequenceLength, currLength);
		}
		return maxConsecutiveSequenceLength;
	}

	private static class ListNode {
		int value;
		ListNode next;
		ListNode previous;

		public ListNode(int value) {
			this.value = value;
		}
	}

	public static int longestConsecutiveV3(int[] nums) {
		int maxConsecutiveSequenceLength = 0;
		if (nums != null && nums.length > 0) {
			Map<Integer, ListNode> map = new HashMap<>();
			for (int num : nums) {
				ListNode currNode = map.get(num);
				if (currNode == null) {
					currNode = new ListNode(num);
					map.put(num, currNode);
				}

				if (num != Integer.MIN_VALUE && num != Integer.MAX_VALUE) {
					ListNode previousNode = map.get(num - 1);
					if (previousNode != null) {
						currNode.previous = previousNode;
						previousNode.next = currNode;
					}
					ListNode nextNode = map.get(num + 1);
					if (nextNode != null) {
						currNode.next = nextNode;
						nextNode.previous = currNode;
					}
				}
			}

			Set<Integer> visited = new HashSet<>();
			for (Integer num : map.keySet()) {
				if (!visited.contains(num)) {
					int currLength = 0;
					ListNode currNode = map.get(num);
					ListNode temp = currNode;
					while (currNode != null) {
						visited.add(currNode.value);
						++currLength;
						currNode = currNode.next;
					}
					currNode = temp;
					while (currNode != null) {
						visited.add(currNode.value);
						++currLength;
						currNode = currNode.previous;
					}
					currLength -= 1;
					maxConsecutiveSequenceLength = Math.max(maxConsecutiveSequenceLength, currLength);
				}
			}

		}
		return maxConsecutiveSequenceLength;
	}

	public static int longestConsecutiveV4(int[] nums) {
		int maxConsecutiveSequenceLength = 0;
		if (nums != null && nums.length > 0) {
			Set<Integer> set = new HashSet<>();
			for (int num : nums) {
				set.add(num);
			}
			Set<Integer> visited = new HashSet<>();
			for (int num : nums) {
				if (!visited.contains(num)) {
					int temp = num;
					int currLength = 0;
					while (true) {
						if (set.contains(num)) {
							visited.add(num);
							++currLength;
							if (num == Integer.MAX_VALUE) {
								break;
							}
							num = num + 1;
						} else {
							break;
						}
					}
					num = temp;
					while (true) {
						if (set.contains(num)) {
							visited.add(num);
							++currLength;
							if (num == Integer.MIN_VALUE) {
								break;
							}
							num = num - 1;
						} else {
							break;
						}
					}
					currLength -= 1;
					maxConsecutiveSequenceLength = Math.max(maxConsecutiveSequenceLength, currLength);
				}
			}
		}
		return maxConsecutiveSequenceLength;
	}

	public static int longestConsecutiveV5(int[] nums) {
		int maxConsecutiveSequenceLength = 0;
		if (nums != null && nums.length > 0) {
			Set<Integer> set = new HashSet<>();
			for (int num : nums) {
				set.add(num);
			}
			for (int num : nums) {
				if (set.contains(num)) {
					int temp = num;
					int currLength = 0;
					while (true) {
						if (set.contains(num)) {
							++currLength;
							if (num == Integer.MAX_VALUE) {
								break;
							}
							set.remove(num);
							num = num + 1;
						} else {
							break;
						}
					}
					num = temp;
					set.add(num);
					while (true) {
						if (set.contains(num)) {
							++currLength;
							if (num == Integer.MIN_VALUE) {
								break;
							}
							set.remove(num);
							num = num - 1;
						} else {
							break;
						}
					}
					currLength -= 1;
					maxConsecutiveSequenceLength = Math.max(maxConsecutiveSequenceLength, currLength);
				}
			}
		}
		return maxConsecutiveSequenceLength;
	}

	public static void main(String[] args) {
		int[] nums = { 2147483646, -2147483647, 0, 2, 2147483644, -2147483645, 2147483645 };
		int maxConsecutiveSequenceLength = longestConsecutiveV5(nums);
		System.out.println(maxConsecutiveSequenceLength);
	}

}
