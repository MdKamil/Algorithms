package algorithm.five;

public class FruitIntoBaskets {

	public static int totalFruit(int[] tree) {
		int maxFruitCollected = 1;
		if (tree.length == 1) {
			maxFruitCollected = 1;
		} else {
			int type1 = -1;
			int type1Count = 0;
			int lastIdxOfType1 = -1;
			int type2 = -1;
			int type2Count = 0;
			int lastIdxOfType2 = -1;
			maxFruitCollected = 0;
			for (int idx = 0; idx <= tree.length - 1; ++idx) {
				if (type1 == -1) {
					type1 = tree[idx];
					++type1Count;
					lastIdxOfType1 = idx;
				} else if (tree[idx] == type1) {
					++type1Count;
					lastIdxOfType1 = idx;
				} else if (type2 == -1) {
					type2 = tree[idx];
					++type2Count;
					lastIdxOfType2 = idx;
				} else if (tree[idx] == type2) {
					++type2Count;
					lastIdxOfType2 = idx;
				} else {
					maxFruitCollected = Math.max(maxFruitCollected, type1Count + type2Count);
					if (tree[idx - 1] == type1) {
						type1Count = ((idx - 1) - lastIdxOfType2);
						type2 = tree[idx];
						type2Count = 1;
						lastIdxOfType2 = idx;
					} else if (tree[idx - 1] == type2) {
						type2Count = ((idx - 1) - lastIdxOfType1);
						type1 = tree[idx];
						type1Count = 1;
						lastIdxOfType1 = idx;
					}
				}
			}
			maxFruitCollected = Math.max(maxFruitCollected, type1Count + type2Count);
		}
		return maxFruitCollected;
	}

	public static void main(String[] args) {
		int[] tree = { 1, 0, 2, 3, 4 };
		int maxFruit = totalFruit(tree);
		System.out.println(maxFruit);
	}

}
