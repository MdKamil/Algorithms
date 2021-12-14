package algorithm.array;

import java.util.Map;
import java.util.TreeMap;

public class SnapshotArray {

	private TreeMap<Integer, Integer>[] idxArray;
	private int snap = 0;

	public SnapshotArray(int length) {
		idxArray = new TreeMap[100];
		for (int idx = 0; idx <= idxArray.length - 1; ++idx) {
			idxArray[idx] = new TreeMap<>();
		}
	}

	public void set(int index, int val) {
		TreeMap<Integer, Integer> snapMap = idxArray[index];
		snapMap.put(snap, val);
	}

	public int snap() {
		return snap++;
	}

	public int get(int index, int snap_id) {
		TreeMap<Integer, Integer> snapMap = idxArray[index];
		Map.Entry<Integer, Integer> recentSnap = snapMap.floorEntry(snap_id);
		return (recentSnap == null ? 0 : recentSnap.getValue());
	}

	public static void main(String[] args) {
		SnapshotArray snapshotArray = new SnapshotArray(10);
		snapshotArray.set(0, 11);
		snapshotArray.set(2, 12);
		snapshotArray.snap();
		snapshotArray.set(1, 21);
		snapshotArray.set(2, 25);
		snapshotArray.snap();
		snapshotArray.set(0, 51);
		snapshotArray.set(2, 52);

		System.out.println(snapshotArray.get(7, 0));
	}

}
