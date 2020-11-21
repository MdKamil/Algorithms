package algorithm.array;

public class MaximumProductSubArray {

	public static int maxProduct(int[] nums) {
		int maxProduct = 0;
		if (nums != null && nums.length > 0) {
			if (nums.length == 1) {
				maxProduct = nums[0];
			} else {
				int countOfNegativeNo = 0;
				int firstNegativeNoIdx = -1;
				int productBeforeFirstNegativeNo = 0;
				int productAfterFirstNegativeNo = 0;
				int productBeforeLastNegativeNo = 0;
				int productOfAll = 0;
				for (int idx = 0; idx <= nums.length; ++idx) {
					if (idx == nums.length || nums[idx] == 0) {
						if (countOfNegativeNo % 2 == 0) {
							maxProduct = Math.max(maxProduct, productOfAll);
						} else if (countOfNegativeNo == 1) {
							maxProduct = Math.max(maxProduct,
									Math.max(productBeforeFirstNegativeNo, productAfterFirstNegativeNo));
						} else {
							maxProduct = Math.max(maxProduct,
									Math.max(productAfterFirstNegativeNo, productBeforeLastNegativeNo));
						}
						countOfNegativeNo = 0;
						firstNegativeNoIdx = -1;
						productBeforeFirstNegativeNo = 0;
						productAfterFirstNegativeNo = 0;
						productBeforeLastNegativeNo = 0;
						productOfAll = 0;
					} else {
						int productBeforeCurrIdx = productOfAll;
						productOfAll = (productOfAll == 0 ? nums[idx] : productOfAll * nums[idx]);
						if (nums[idx] < 0) {
							++countOfNegativeNo;
							productBeforeLastNegativeNo = productBeforeCurrIdx;
							if (firstNegativeNoIdx == -1) {
								firstNegativeNoIdx = idx;
								productBeforeFirstNegativeNo = productBeforeCurrIdx;
							} else {
								productAfterFirstNegativeNo = (productAfterFirstNegativeNo == 0 ? nums[idx]
										: productAfterFirstNegativeNo * nums[idx]);
							}
						} else if (firstNegativeNoIdx != -1) {
							productAfterFirstNegativeNo = (productAfterFirstNegativeNo == 0 ? nums[idx]
									: productAfterFirstNegativeNo * nums[idx]);
						}
					}
				}
			}
		}
		return maxProduct;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 3, -2, 4 };
		int maxProduct = maxProduct(nums);
		System.out.println(maxProduct);
	}

}
