package leetCode75;

public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{20, 100, 10, 12, 5, 13}));
    }

    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
