package algorithm.online;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Allocation {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter writer = new PrintWriter(System.out);
		int totalNoOfTestCase = in.nextInt();
		for (int testCaseNo = 1; testCaseNo <= totalNoOfTestCase; ++testCaseNo) {
			int noOfHouses = in.nextInt();
			int totalBudget = in.nextInt();
			int[] homePrice = new int[noOfHouses];
			for (int idx = 1; idx <= noOfHouses; ++idx) {
				homePrice[idx - 1] = in.nextInt();
			}
			Arrays.sort(homePrice);
			int homeCount = 0;
			for (int price : homePrice) {
				totalBudget -= price;
				if (totalBudget < 0) {
					break;
				} else if (totalBudget == 0) {
					++homeCount;
					break;
				} else {
					++homeCount;
				}
			}
			writer.println("Case #" + testCaseNo + ": " + homeCount);
		}
		in.close();
		writer.flush();
		writer.close();
	}
}
