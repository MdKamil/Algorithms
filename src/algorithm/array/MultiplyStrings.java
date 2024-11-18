package algorithm.array;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }
        char[] num1Array = num1.toCharArray();
        char[] num2Array = num2.toCharArray();
        char[] temp = new char[num1.length() + num2.length()];
        int tempIdx = temp.length - 1;
        int tempTempIdx = temp.length - 1;
        for (int num2ArrayIdx = num2Array.length - 1; num2ArrayIdx >= 0; --num2ArrayIdx) {
            int carry = 0;
            for (int num1ArrayIdx = num1Array.length - 1; num1ArrayIdx >= 0; --num1ArrayIdx) {
                int num2AsInt = Character.getNumericValue(num2Array[num2ArrayIdx]);
                int num1AsInt = Character.getNumericValue(num1Array[num1ArrayIdx]);
                int multipliedResult = (num1AsInt * num2AsInt) + carry;
                carry = multipliedResult / 10;
                if (temp[tempIdx] != '\0') {
                    if ((multipliedResult % 10) + Character.getNumericValue(temp[tempIdx]) > 9) {
                        int val = multipliedResult % 10;
                        int carryIdx = tempIdx;
                        while (Character.getNumericValue(temp[carryIdx]) > -1 && val + Character.getNumericValue(temp[carryIdx]) > 9) {
                            int result = val + Character.getNumericValue(temp[carryIdx]);
                            temp[carryIdx] = Character.forDigit(result % 10, 10);
                            --carryIdx;
                            val = result / 10;
                        }
                        temp[carryIdx] = Character.getNumericValue(temp[carryIdx]) > -1 ? Character.forDigit(val + Character.getNumericValue(temp[carryIdx]), 10) :
                                Character.forDigit(val, 10);
                    } else {
                        temp[tempIdx] = Character.forDigit((multipliedResult % 10) + Character.getNumericValue(temp[tempIdx]), 10);
                    }
                } else {
                    temp[tempIdx] = Character.forDigit(multipliedResult % 10, 10);
                }
                --tempIdx;
                if (num1ArrayIdx == 0 && carry > 0) {
                    temp[tempIdx] = Character.getNumericValue(temp[tempIdx]) > -1 ? Character.forDigit(carry + Character.getNumericValue(temp[tempIdx]), 10) :
                            Character.forDigit(carry, 10);
                }
            }
            tempIdx = --tempTempIdx;
        }
        return String.valueOf(temp).trim();
    }

    public static void main(String[] args) {
        String num1 = "6913259244";
        String num2 = "71103343";
        String multipliedResult = new MultiplyStrings().multiply(num1, num2);
        System.out.println(multipliedResult);
    }
}
