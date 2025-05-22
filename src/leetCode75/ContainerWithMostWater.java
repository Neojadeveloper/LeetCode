package leetCode75;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length-1;
        while (left < right) {
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            int lh = height[left];
            int rh = height[right];
            maxArea = Math.max(maxArea, currentArea);
            if (height[left] < height[right]) {
                while (left<right && height[left] <= lh)
                    left++;
            } else {
                while (left<right && height[right] <= rh)
                    right--;
            }
        }
        return maxArea;
    }
}
