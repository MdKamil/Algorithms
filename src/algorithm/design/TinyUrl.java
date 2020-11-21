package algorithm.design;

public class TinyUrl {

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int BASE = ALPHABET.length();

	public static String encode(int num) {
		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			sb.append(ALPHABET.charAt(num % BASE));
			num /= BASE;
		}
		return sb.reverse().toString();
	}

	public static int decode(String str) {
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			num = (num * BASE) + ALPHABET.indexOf(str.charAt(i));
			System.out.println(num);
		}
		return num;
	}

	public static void main(String[] args) {
		 String encodedString = encode(12345678);
		 System.out.println("Encoded: " + encodedString);
		
		 // System.out.println(Integer.parseInt("12345678",62));
		
		 int decodedInt = decode(encodedString);
		 System.out.println("Decoded: " + decodedInt);

		//System.out.println(Integer.parseInt("0AAA", 14));

	}
}
