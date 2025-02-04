package leetCode75;

import java.util.ArrayList;
import java.util.List;

public class kidsWithTheGreatestNumberOfCandies {
    public static void main(String[] args) {
        System.out.println(kidsWithCandies(new int[]{1, 2, 3, 4}, 1));
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int top = 0;
        for (int candy : candies) {
            if (candy > top) top = candy;
        }
        ArrayList<Boolean> isExtra = new ArrayList<>();
        for (int candy : candies) {
            if (candy + extraCandies >= top) isExtra.add(true);
            else isExtra.add(false);
        }
        return isExtra;
    }
}
