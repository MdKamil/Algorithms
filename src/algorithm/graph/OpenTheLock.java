package algorithm.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock {

	public static int openLock(String[] deadends, String target) {
		int minTurns = 0;
		Set<String> set = new HashSet<>();
		Set<String> dead = new HashSet<>();
		for (String deadStr : deadends) {
			dead.add(deadStr);
		}
		String start = "0000";
		boolean found = false;
		if (start.equals(target)) {
			found = true;
		} else if (!dead.contains(start)) {
			Queue<String> queue = new ArrayDeque<>();
			queue.add(start);
			set.add(start);
			outer: while (!queue.isEmpty()) {
				int size = queue.size();
				for (int count = 1; count <= size; ++count) {
					char[] str = queue.poll().toCharArray();
					for (int idx = 0; idx <= 3; ++idx) {
						char actualChar = str[idx];
						int c = Character.digit(actualChar, 10);
						int up = c == 9 ? 0 : c + 1;
						int down = c == 0 ? 9 : c - 1;
						str[idx] = Character.forDigit(up, 10);
						String upStr = new String(str);
						if (upStr.equals(target)) {
							found = true;
							++minTurns;
							break outer;
						} else if (!dead.contains(upStr) && !set.contains(upStr)) {
							set.add(upStr);
							queue.add(upStr);
						}
						str[idx] = Character.forDigit(down, 10);
						String downStr = new String(str);
						if (downStr.equals(target)) {
							found = true;
							++minTurns;
							break outer;
						} else if (!dead.contains(downStr) && !set.contains(downStr)) {
							set.add(downStr);
							queue.add(downStr);
						}
						str[idx] = actualChar;
					}
				}
				++minTurns;
			}
		}
		return found ? minTurns : -1;
	}

	public static int openLockV2(String[] deadends, String target) {
		Set<String> begin = new HashSet<>();
		Set<String> end = new HashSet<>();
		Set<String> deads = new HashSet<>(Arrays.asList(deadends));
		begin.add("0000");
		end.add(target);
		int level = 0;
		Set<String> temp;
		while (!begin.isEmpty() && !end.isEmpty()) {
			if (begin.size() > end.size()) {
				temp = begin;
				begin = end;
				end = temp;
			}
			temp = new HashSet<>();
			for (String s : begin) {
				if (end.contains(s))
					return level;
				if (deads.contains(s))
					continue;
				deads.add(s);
				StringBuilder sb = new StringBuilder(s);
				for (int i = 0; i < 4; i++) {
					char c = sb.charAt(i);
					String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
					String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
					if (!deads.contains(s1))
						temp.add(s1);
					if (!deads.contains(s2))
						temp.add(s2);
				}
			}
			level++;
			begin = temp;
		}
		return -1;
	}

	public static void main(String[] args) {
		String[] deadends = { "8888" };
		String target = "0000";
		System.out.println(openLock(deadends, target));
	}

}
