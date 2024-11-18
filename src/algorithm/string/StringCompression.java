package algorithm.string;

import java.util.Arrays;
import java.util.Objects;

public class StringCompression {
    public int compress(char[] chars) {
        if (chars.length < 2) {
            return chars.length;
        } else {
            char prevChar = chars[0];
            int currCharCount = 1;
            int insertionIdx = 0;
            for (int idx = 1; idx <= chars.length - 1; ++idx) {
                if (Objects.equals(chars[idx], prevChar)) {
                    ++currCharCount;
                } else {
                    insertionIdx = insertInArray(currCharCount, chars, insertionIdx, prevChar);
                    currCharCount = 1;
                    prevChar = chars[idx];
                }
            }
            return insertInArray(currCharCount, chars, insertionIdx, prevChar);
        }
    }

    private int insertInArray(int currCharCount, char[] chars, int insertionIdx, char prevChar) {
        if (currCharCount == 1) {
            chars[insertionIdx++] = prevChar;
        } else if (currCharCount > 1 && currCharCount < 10) {
            chars[insertionIdx++] = prevChar;
            chars[insertionIdx++] = Character.forDigit(currCharCount, 10);
        } else {
            int length = getLength(currCharCount);
            int indexToInsert = insertionIdx + length;
            while (currCharCount > 0) {
                chars[indexToInsert--] = Character.forDigit(currCharCount % 10, 10);
                currCharCount /= 10;
            }
            chars[indexToInsert] = prevChar;
            insertionIdx += length + 1;
        }
        return insertionIdx;
    }

    private int getLength(int currCharCount) {
        int length = 0;
        if (currCharCount > 9 && currCharCount <= 99) {
            length = 2;
        } else if (currCharCount > 99 && currCharCount <= 999) {
            length = 3;
        } else if (currCharCount > 999 && currCharCount <= 9999) {
            length = 4;
        }
        return length;
    }

    public static void main(String[] args) {
        String s = "";
        char[] chars = s.toCharArray();
        int length = new StringCompression().compress(chars);
        System.out.println(Arrays.toString(chars));
        System.out.println(length);
    }
}
