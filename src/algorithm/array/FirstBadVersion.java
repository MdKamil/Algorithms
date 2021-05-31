package algorithm.array;

class VersionControl {
	public static boolean isBadVersion(int version) {
		return version == 2147483647 ? true : false;
	}
}

public class FirstBadVersion extends VersionControl {
	public static int firstBadVersion(int n) {
		int firstBadversion = 1;
		int low = 1;
		int high = n;
		if (n != 1) {
			while (low <= high) {
				long mid = ((long) low + (long) high) / 2;
				boolean isBad = isBadVersion((int) mid);
				if (isBad) {
					firstBadversion = (int) mid;
					high = (int) mid - 1;
				} else {
					low = (int) mid + 1;
				}
			}
		}
		return firstBadversion;
	}

	public static void main(String[] args) {
		int n = 2147483647;
		int version = firstBadVersion(n);
		System.out.println(version);
	}
}
