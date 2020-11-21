package algorithm.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {

	public static String simplifyPath(String path) {
		StringBuilder pathBuilder = new StringBuilder();
		if (path != null) {
			StringBuilder directoryBuilder = new StringBuilder();
			Deque<String> deque = new ArrayDeque<>();
			for (int idx = 1; idx <= path.length() - 1; ++idx) {
				if (path.charAt(idx) == '.') {
					directoryBuilder.append(path.charAt(idx));
					int fromIdx = idx + 1;
					for (; fromIdx <= path.length() - 1; ++fromIdx) {
						if (path.charAt(fromIdx) != '/') {
							directoryBuilder.append(path.charAt(fromIdx));
						} else {
							break;
						}
					}
					String dir = directoryBuilder.toString();
					if (directoryBuilder.length() == 2 && (dir.charAt(0) == '.' && dir.charAt(1) == '.')) {
						if (!deque.isEmpty()) {
							deque.pollLast();
						}
					} else if (directoryBuilder.length() > 2) {
						deque.add(dir);
					}
					directoryBuilder.setLength(0);
					idx = fromIdx;
				} else if (path.charAt(idx) != '/') {
					directoryBuilder.append(path.charAt(idx));
				} else if (path.charAt(idx) == '/') {
					if (directoryBuilder.length() > 0) {
						deque.add(directoryBuilder.toString());
						directoryBuilder.setLength(0);
					}
				}
			}
			if (directoryBuilder.length() > 0) {
				deque.add(directoryBuilder.toString());
				directoryBuilder.setLength(0);
			}
			pathBuilder.append('/');
			while (!deque.isEmpty()) {
				pathBuilder.append(deque.poll());
				pathBuilder.append("/");
			}
			if (pathBuilder.length() > 1) {
				pathBuilder.deleteCharAt(pathBuilder.length() - 1);
			}
		}
		return pathBuilder.toString();
	}

	public static void main(String[] args) {
		String path = "/home/...";
		String canonicalPath = simplifyPath(path);
		System.out.println(canonicalPath);
	}

}
