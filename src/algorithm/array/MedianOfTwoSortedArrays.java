package algorithm.array;

public class MedianOfTwoSortedArrays {

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		double median = -1;
		int totalLength = nums1.length + nums2.length;
		int medianLength = (totalLength % 2 == 0 ? (totalLength / 2) - 1 : totalLength / 2);
		int currLength = -1;
		boolean isFound = false;
		int idx1 = 0;
		int idx2 = 0;
		while (idx1 <= nums1.length - 1 && idx2 <= nums2.length - 1) {
			if (nums1[idx1] <= nums2[idx2]) {
				++idx1;
				++currLength;
				if (currLength == medianLength) {
					if (totalLength % 2 != 0) {
						median = (double) nums1[idx1 - 1];
					} else {
						if (idx1 <= nums1.length - 1) {
							if (nums1[idx1] <= nums2[idx2]) {
								median = ((double) nums1[idx1 - 1] + (double) nums1[idx1]) / (double) 2;
							} else {
								median = ((double) nums1[idx1 - 1] + (double) nums2[idx2]) / (double) 2;
							}
						} else {
							median = ((double) nums1[idx1 - 1] + (double) nums2[idx2]) / (double) 2;
						}
					}
					isFound = true;
					break;
				}
			} else {
				++idx2;
				++currLength;
				if (currLength == medianLength) {
					if (totalLength % 2 != 0) {
						median = (double) nums2[idx2 - 1];
					} else {
						if (idx2 <= nums2.length - 1) {
							if (nums2[idx2] <= nums1[idx1]) {
								median = ((double) nums2[idx2 - 1] + (double) nums2[idx2]) / (double) 2;
							} else {
								median = ((double) nums2[idx2 - 1] + (double) nums1[idx1]) / (double) 2;
							}
						} else {
							median = ((double) nums2[idx2 - 1] + (double) nums1[idx1]) / (double) 2;
						}
					}
					isFound = true;
					break;
				}
			}
		}
		if (!isFound) {
			if (idx1 > nums1.length - 1) {
				idx2 = idx2 + (medianLength - (idx1 + idx2));
				if (totalLength % 2 != 0) {
					median = (double) nums2[idx2];
				} else {
					median = ((double) nums2[idx2] + (double) nums2[idx2 + 1]) / (double) 2;
				}
			} else if (idx2 > nums2.length - 1) {
				idx1 = idx1 + (medianLength - (idx1 + idx2));
				if (totalLength % 2 != 0) {
					median = (double) nums1[idx1];
				} else {
					median = ((double) nums1[idx1] + (double) nums1[idx1 + 1]) / (double) 2;
				}
			}
		}
		return median;
	}

	public static void main(String[] args) {
		int[] nums1 = { 8, 9 };
		int[] nums2 = { 2 };
		double median = findMedianSortedArrays(nums1, nums2);
		System.out.println(median);
	}

}
