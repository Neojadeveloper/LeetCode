package leetCode75;

import java.util.HashMap;
import java.util.Map;

public class LeetCode75 {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        // Faqat kerakli oraliqda bo'lgan sonlarni hisoblash
        for (int num : nums) {
            if (num >= 0 && num < k) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
        }

        int result = 0;
        for (int i : freq.keySet()) {
            int complement = k - i;

            // i < complement bo‘lsa, biz faqat bir marta hisoblaymiz
            if (i < complement && freq.containsKey(complement)) {
                result += Math.min(freq.get(i), freq.get(complement));
            }
        }

        // Agar k juft bo‘lsa, k/2 + k/2 = k juftliklaridan yarmini olamiz
        if (k % 2 == 0) {
            int mid = freq.getOrDefault(k / 2, 0);
            result += mid / 2;
        }

        return result;
    }
}
