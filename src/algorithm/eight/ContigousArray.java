package algorithm.eight;

import java.util.HashMap;
import java.util.Map;

public class ContigousArray {

	public static int findMaxLengthV1(int[] nums) {
		int maxLength = 0;
		if (nums != null && nums.length > 0) {
			int oneCount = 0;
			int zeroCount = 0;
			for (int idx = 0; idx <= nums.length - 1; ++idx) {
				for (int idx1 = idx; idx1 <= nums.length - 1; ++idx1) {
					if (nums[idx1] == 1) {
						++oneCount;
					} else {
						++zeroCount;
					}

					if (oneCount == zeroCount) {
						maxLength = Math.max(maxLength, oneCount * 2);
					}
				}
				oneCount = 0;
				zeroCount = 0;
			}
		}
		return maxLength;
	}

	private static int addToMap(int oneCount, int zeroCount, int idx, Map<Integer, Integer> map, int maxLength) {
		int diff = Math.abs(oneCount - zeroCount);
		Integer diffIdx = map.get(diff);
		if (diffIdx == null) {
			map.put(diff, idx);
		} else {
			maxLength = Math.max(maxLength, idx - diffIdx);
		}
		return maxLength;
	}

	public static int findMaxLengthV2(int[] nums) {
		int maxLength = 0;
		if (nums != null && nums.length > 0) {
			Map<Integer, Integer> zeroGreater = new HashMap<>();
			Map<Integer, Integer> oneGreater = new HashMap<>();
			int oneCount = 0;
			int zeroCount = 0;
			for (int idx = 0; idx <= nums.length - 1; ++idx) {
				if (nums[idx] == 1) {
					++oneCount;
				} else {
					++zeroCount;
				}
				if (oneCount == zeroCount) {
					maxLength = Math.max(maxLength, oneCount * 2);
				}
				if (oneCount > zeroCount) {
					int diff = oneCount - zeroCount;
					Integer diffIdx = oneGreater.get(diff);
					if (diffIdx == null) {
						oneGreater.put(diff, idx);
					} else {
						maxLength = Math.max(maxLength, idx - diffIdx);
					}
				} else if (oneCount < zeroCount) {
					int diff = zeroCount - oneCount;
					Integer diffIdx = zeroGreater.get(diff);
					if (diffIdx == null) {
						zeroGreater.put(diff, idx);
					} else {
						maxLength = Math.max(maxLength, idx - diffIdx);
					}
				}
			}
		}
		return maxLength;
	}

	public static int findMaxLengthV3(int[] nums) {
		int maxLength = 0;
		if (nums != null && nums.length > 0) {
			Map<Integer, Integer> zeroGreater = new HashMap<>();
			Map<Integer, Integer> oneGreater = new HashMap<>();
			int oneCount = 0;
			int zeroCount = 0;
			for (int idx = 0; idx <= nums.length - 1; ++idx) {
				if (nums[idx] == 1) {
					++oneCount;
				} else {
					++zeroCount;
				}
				if (oneCount == zeroCount) {
					maxLength = Math.max(maxLength, oneCount * 2);
				} else if (oneCount > zeroCount) {
					maxLength = addToMap(oneCount, zeroCount, idx, oneGreater, maxLength);
				} else if (oneCount < zeroCount) {
					maxLength = addToMap(oneCount, zeroCount, idx, zeroGreater, maxLength);
				}
			}
		}
		return maxLength;
	}

	// 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0
	// 0, 0, 1, 0, 0, 0, 1, 1
	// 1, 1, 1, 0, 0, 0, 0, 0, 0, 1
	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 };
		int maxLength = findMaxLengthV1(nums);
		System.out.println(maxLength);
		maxLength = findMaxLengthV3(nums);
		System.out.println(maxLength);
	}

}
