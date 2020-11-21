package algorithm.string;

import java.util.Arrays;
import java.util.Comparator;

public class ReorderDataInLog {

	public static String[] reorderLogFiles(String[] logs) {
		int swapIdx = logs.length;
		for (int idx = logs.length - 1; idx >= 0; --idx) {
			String log = logs[idx];
			String[] logWord = log.split("\\s");
			boolean isDigit = true;
			for (char c : logWord[1].toCharArray()) {
				if (!Character.isDigit(c)) {
					isDigit = false;
					break;
				}
			}
			if (isDigit) {
				--swapIdx;
				swap(idx, swapIdx, logs);
			}
		}
		Comparator<String> c = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String[] s1Arr = s1.split("\\s");
				String[] s2Arr = s2.split("\\s");
				int idx = 1;
				int compResult = 0;
				while (idx <= s1Arr.length - 1 && idx <= s2Arr.length - 1) {
					compResult = s1Arr[idx].compareTo(s2Arr[idx]);
					if (compResult != 0) {
						break;
					}
					++idx;
				}
				if (compResult != 0) {
					return compResult;
				}
				if (s1Arr.length > s2Arr.length) {
					return 1;
				} else if (s2Arr.length > s1Arr.length) {
					return -1;
				} else {
					return s1Arr[0].compareTo(s2Arr[0]);
				}
			}
		};
		Arrays.sort(logs, 0, swapIdx, c);
		return logs;
	}

	private static void swap(int idx, int swapIdx, String[] logs) {
		String temp = logs[swapIdx];
		logs[swapIdx] = logs[idx];
		logs[idx] = temp;
	}

	public static void main(String[] args) {
		String[] logs = { "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero" };
		reorderLogFiles(logs);
		for (String log : logs) {
			System.out.println(log);
		}
	}

}
