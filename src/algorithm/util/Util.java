package algorithm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

	public static boolean checkTwoWordsDifferBySingleEdit(String word1, String word2) {
		boolean differ = false;
		int[] w1 = new int[26];
		int[] w2 = new int[26];
		for (char c : word1.toCharArray()) {
			w1[(int) c - 97] += 1;
		}
		for (char c : word2.toCharArray()) {
			w2[(int) c - 97] += 1;
		}
		int d1 = 0;
		int d2 = 0;
		for (int i = 0; i <= 25; ++i) {
			if (w1[i] != w2[i]) {
				if (w1[i] > w2[i]) {
					d1 += w1[i] - w2[i];
				} else {
					d2 += w2[i] - w1[i];
				}
			}
		}
		if (d1 == 1 && d2 == 1) {
			differ = true;
		}
		return differ;
	}

	public static void generateRandomString() {
		Random random = new Random();
		List<String> wordList = new ArrayList<>();
		String[] array = new String[100];
		char[] word = new char[5];
		for (int iter = 1; iter <= 100; ++iter) {
			for (int i = 0; i <= word.length - 1; ++i) {
				word[i] = (char) (97 + random.nextInt(26));
			}
			wordList.add(new String(word));
		}
		System.out.println(wordList);
	}

	public static void main(String[] args) {
		generateRandomString();
	}

}
