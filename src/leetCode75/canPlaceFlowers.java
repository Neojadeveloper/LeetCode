package leetCode75;

public class canPlaceFlowers {
    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 0, 1}, 2));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int c = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                boolean l = i == 0 || flowerbed[i - 1] == 0;
                boolean r = flowerbed.length - 1 == i || flowerbed[i + 1] == 0;
                if (l && r) {
                    c++;
                    i++;
                }
                if (c == n) return true;
            }
        }
        return c>=n;
    }
}
