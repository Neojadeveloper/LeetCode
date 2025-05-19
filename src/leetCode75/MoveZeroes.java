package leetCode75;

import util.Util;

public class MoveZeroes {
    public static void main(String[] args) {
        moveZeroes(new int[]{1,0});
    }

    public static void moveZeroes(int[] nums) {
        int length = nums.length;
        if (length == 1) return;
        int read = 0;
        int swap = 1;
        while (read < length - swap) {
            if (nums[read] == 0) {
                if (nums[read + swap] == 0) {
                    swap++;
                    continue;
                }
                nums[read] = nums[read + swap];
                nums[read + swap] = 0;
            }
            read++;
        }
        System.out.println(Util.array2String(nums));
    }
}
