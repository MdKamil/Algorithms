package algorithm.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class TimeMap {
    private final Map<String, TreeMap<Integer, String>> kvStore;

    public TimeMap() {
        kvStore = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!kvStore.containsKey(key)) {
            TreeMap<Integer, String> timestampMap = new TreeMap<>();
            timestampMap.put(timestamp, value);
            kvStore.put(key, timestampMap);
        } else {
            TreeMap<Integer, String> timestampMap = kvStore.get(key);
            timestampMap.put(timestamp, value);
        }
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> timestampMap = kvStore.get(key);
        if (Objects.isNull(timestampMap)) {
            return "";
        }
        Map.Entry<Integer, String> timestampEntry = timestampMap.floorEntry(timestamp);
        return Objects.isNull(timestampEntry) ? "" : timestampEntry.getValue();
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        System.out.println(timeMap.get("foo", 1));
        System.out.println(timeMap.get("foo", 3));
        timeMap.set("foo", "bar2", 4);
        System.out.println(timeMap.get("foo", 4));
        System.out.println(timeMap.get("foo", 5));
    }
}