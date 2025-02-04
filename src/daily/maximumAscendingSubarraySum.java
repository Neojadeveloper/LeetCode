package daily;

public class maximumAscendingSubarraySum {
    public static void main(String[] args) {
        System.out.println(maxAscendingSum(new int[]{10, 20, 30, 5, 10, 50}));
    }

    public static int maxAscendingSum(int[] nums) {
        int min = nums[0];
        int t = nums[0];
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (min < nums[i]) {
                t += nums[i];
                min = nums[i];
            } else {
                res = Math.max(Math.max(t, nums[i]),res);
                t = nums[i];
                min = nums[i];
            }
        }
        return Math.max(res, t);
    }
}