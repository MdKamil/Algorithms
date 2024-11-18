package algorithm.array;

public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int k = Integer.MAX_VALUE;
        int min = 1;
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(pile, max);
        }
        while (min <= max) {
            int mid = (min + max) / 2;
            long count = 0;
            for (int pile : piles) {
                count += (int) Math.ceil((double) pile / mid);
            }
            if (count <= h) {
                max = mid - 1;
                k = Math.min(k, mid);
            } else {
                min = mid + 1;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] piles = {805306368, 805306368, 805306368};
        int h = 1000000000;
        int k = new KokoEatingBananas().minEatingSpeed(piles, h);
        System.out.println(k);
    }
}
