package algorithm.online;

public class Amplitude {
	public static String solution(int[] T) {
		String[] season = { "WINTER", "SPRING", "SUMMER", "AUTUMN" };
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int maxAmplitude = Integer.MIN_VALUE;
		int maxInSeason = -1;
		int seasonIdx = 0;
		for (int idx = 0; idx <= T.length - 1; ++idx) {
			if (min == Integer.MAX_VALUE && max == Integer.MIN_VALUE) {
				min = T[idx];
				max = T[idx];
			}
			if (T[idx] < min) {
				min = T[idx];
			}
			if (T[idx] > max) {
				max = T[idx];
			}
			if (((idx + 1) % (T.length / 4)) == 0) {
				if (max - min > maxAmplitude) {
					maxAmplitude = max - min;
					maxInSeason = seasonIdx;
				}
				min = Integer.MAX_VALUE;
				max = Integer.MIN_VALUE;
				++seasonIdx;
			}
		}
		return season[maxInSeason];
	}

	public static void main(String[] args) {
		int[] T = { 2, -3, 3, 1, 10, 8, 2, 5, 13, -5, 3, -18 };
		String season = solution(T);
		System.out.println(season);
	}
}
