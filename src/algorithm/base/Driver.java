package algorithm.base;

import java.util.HashMap;
import java.util.Map;

public class Driver {
	public static void main(String[] args) {
		Map<String, Integer> messageMap = new HashMap<>();
		Integer time = messageMap.get("foo");
		if (time == null) {
			System.out.println("null");
		} else {
			System.out.println(time);
		}

	}
}
