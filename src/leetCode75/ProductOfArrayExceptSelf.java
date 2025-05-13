package leetCode75;

import util.Util;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        System.out.println(Util.array2String(productExceptSelf(new int[]{1, 2, 3, 4})));
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        Arrays.fill(answer, 1);
        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            answer[i] *= left;
            left *= nums[i];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] *= right;
            right *= nums[i];
        }
        return answer;
    }
}
