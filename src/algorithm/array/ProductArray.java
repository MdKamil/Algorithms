package algorithm.array;

import java.util.Arrays;

public class ProductArray {

	public static int[] productExceptSelfV1(int[] nums) {
		int[] result = new int[nums.length];
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		for (int i = 0, j = nums.length - 1; i <= nums.length - 1; ++i, --j) {
			left[i] = nums[i] * (i == 0 ? 1 : left[i - 1]);
			right[j] = nums[j] * (j == nums.length - 1 ? 1 : right[j + 1]);
		}
		if (nums.length == 1) {
			result[0] = 0;
		} else {
			for (int i = 0; i <= nums.length - 1; ++i) {
				if (i == 0) {
					result[i] = right[i + 1];
				} else if (i == nums.length - 1) {
					result[i] = left[i - 1];
				} else {
					result[i] = left[i - 1] * right[i + 1];
				}
			}
		}
		return result;
	}

	public static int[] productExceptSelfV2(int[] nums) {
		int[] result = new int[nums.length];
		if (nums.length == 1) {
			result[0] = 0;
		} else {
			int leftProd = 1;
			getProduct(0, nums, result, leftProd);
		}
		return result;
	}

	private static int getProduct(int i, int[] nums, int[] result, int leftProd) {
		if (i == nums.length - 1) {
			result[i] = leftProd;
			return nums[i];
		}
		int rightProd = getProduct(i + 1, nums, result, leftProd * nums[i]);
		result[i] = leftProd * rightProd;
		return rightProd * nums[i];
	}

	public static int[] productExceptSelfV3(int[] nums) {
		int[] result = new int[nums.length];
		if (nums.length == 1) {
			result[0] = 0;
		} else {
			for (int idx = 0; idx <= nums.length - 2; ++idx) {
				if (idx == 0) {
					result[idx] = nums[idx] * 1;
				} else {
					result[idx] = nums[idx] * result[idx - 1];
				}
				if (idx == nums.length - 2) {
					result[idx + 1] = result[idx];
				}
			}
			int rightProd = nums[nums.length - 1];
			for (int idx = nums.length - 2; idx >= 1; --idx) {
				result[idx] = rightProd * result[idx - 1];
				rightProd = rightProd * nums[idx];
			}
			result[0] = rightProd;
		}
		return result;
	}

	public static int[] productExceptSelfV5(int[] nums) {
		int[] result = new int[nums.length];
		if (nums.length > 1) {
			int idx = 0;
			result[idx] = nums[idx] * 1;
			++idx;
			for (; idx <= nums.length - 2; ++idx) {
				result[idx] = nums[idx] * result[idx - 1];
			}
			result[idx] = result[idx - 1];
			int rightProd = nums[nums.length - 1];
			idx = nums.length - 2;
			for (; idx >= 1; --idx) {
				result[idx] = rightProd * result[idx - 1];
				rightProd = rightProd * nums[idx];
			}
			result[0] = rightProd;
		}
		return result;
	}

	// Use of division;
	public static int[] productExceptSelfV4(int[] nums) {
		int[] result = new int[nums.length];
		if (nums.length > 1) {
			int zeroCount = 0;
			int productOfAll = 1;
			for (int num : nums) {
				if (num == 0) {
					++zeroCount;
					if (zeroCount > 1) {
						break;
					}
				} else {
					productOfAll *= num;
				}
			}
			if (zeroCount == 0) {
				for (int idx = 0; idx <= nums.length - 1; ++idx) {
					result[idx] = productOfAll / nums[idx];
				}
			} else if (zeroCount == 1) {
				for (int idx = 0; idx <= nums.length - 1; ++idx) {
					if (nums[idx] == 0) {
						result[idx] = productOfAll;
						break;
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(productExceptSelfV1(nums)));
		System.out.println(Arrays.toString(productExceptSelfV2(nums)));
		System.out.println(Arrays.toString(productExceptSelfV3(nums)));
	}

	private static int[] getRandom() {
		int[] nums = new int[5];
		java.util.Random random = new java.util.Random();
		for (int i = 0; i <= nums.length - 1; ++i) {
			nums[i] = 1 + random.nextInt(6);
		}
		return nums;
	}

}
