package algorithm.one;

public class Permutation {

	public void permute(char[] a, int i) {
		if (i >= a.length - 1) {
			System.out.println(new String(a));
			return;
		}
		for (int j = i; j <= a.length - 1; ++j) {
			swap(a, i, j);
			permute(a, i + 1);
			swap(a, i, j);
		}
	}

	private void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		Permutation permutation = new Permutation();
		char[] a = { 'a', 'b', 'c', 'd', 'e' };
		permutation.permute(a, 0);
		System.out.println("After permuting: " + new String(a));
	}
}
