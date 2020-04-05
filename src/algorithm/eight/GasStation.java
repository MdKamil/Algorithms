package algorithm.eight;

public class GasStation {

	public static int canCompleteCircuit(int[] gas, int[] cost) {
		int startPoint = -1;
		int totalGas = 0;
		int totalCost = 0;
		int length = gas.length;
		for (int idx = 0; idx <= length - 1; ++idx) {
			totalGas += gas[idx];
			totalCost += cost[idx];
		}
		if (totalGas >= totalCost) {
			int startIdx = 0;
			int currIdx = startIdx;
			int currGasLimit = 0;
			while (true) {
				if (currIdx > gas.length - 1) {
					currIdx = 0;
				}
				currGasLimit += gas[currIdx];
				if (currGasLimit >= cost[currIdx]) {
					currGasLimit -= cost[currIdx];
					++currIdx;
					if (currIdx > gas.length - 1) {
						currIdx = 0;
					}
					if (currIdx == startIdx) {
						startPoint = currIdx;
						break;
					}
				} else {
					startIdx = currIdx + 1;
					currIdx = startIdx;
					currGasLimit = 0;
				}
			}
		}
		return startPoint;
	}

	public static void main(String[] args) {
		int[] gas = { 3, 4, 3, 3, 4 };
		int[] cost = { 2, 5, 3, 4, 3 };
		int startPoint = canCompleteCircuit(gas, cost);
		System.out.println(startPoint);
	}
}
