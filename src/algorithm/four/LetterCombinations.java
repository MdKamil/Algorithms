package algorithm.four;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

	public static List<String> letterCombinations(String digits) {
		List<String> combinationList = new ArrayList<>();
		if (digits != null && digits.length() > 0) {
			Map<Character, String> numberToLetterMap = new HashMap<>();
			numberToLetterMap.put('2', "abc");
			numberToLetterMap.put('3', "def");
			numberToLetterMap.put('4', "ghi");
			numberToLetterMap.put('5', "jkl");
			numberToLetterMap.put('6', "mno");
			numberToLetterMap.put('7', "pqrs");
			numberToLetterMap.put('8', "tuv");
			numberToLetterMap.put('9', "wxyz");
			StringBuilder builder = new StringBuilder();
			generateLetterCombinations(0, digits, numberToLetterMap, combinationList, builder);
		}
		return combinationList;
	}

	private static void generateLetterCombinations(int idx, String digits, Map<Character, String> numberToLetterMap,
			List<String> combinationList, StringBuilder builder) {
		if (idx > digits.length() - 1) {
			return;
		}
		String str = numberToLetterMap.get(digits.charAt(idx));
		for (int i = 0; i <= str.length() - 1; ++i) {
			builder.append(str.charAt(i));
			generateLetterCombinations(idx + 1, digits, numberToLetterMap, combinationList, builder);
			if (idx == digits.length() - 1) {
				combinationList.add(builder.toString());
			}
			builder.deleteCharAt(builder.length() - 1);
		}
	}

	public static void main(String[] args) {
		String digits = "";
		List<String> combinationList = letterCombinations(digits);
		for (String str : combinationList) {
			System.out.println(str);
		}
	}

}
