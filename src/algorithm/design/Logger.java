package algorithm.design;

import java.util.HashMap;
import java.util.Map;

public class Logger {

	private Map<String, Integer> messageMap;

	public Logger() {
		messageMap = new HashMap<>();
	}

	public boolean shouldPrintMessage(int timestamp, String message) {
		boolean shouldPrint = false;
		Integer previousTimeStamp = messageMap.get(message);
		if (previousTimeStamp != null) {
			if (timestamp - previousTimeStamp >= 10) {
				messageMap.put(message, timestamp);
				shouldPrint = true;
			}
		} else {
			messageMap.put(message, timestamp);
			shouldPrint = true;
		}
		return shouldPrint;
	}

	public static void main(String[] args) {
		Logger logger = new Logger();
		logger.shouldPrintMessage(1, "foo");
	}

}
