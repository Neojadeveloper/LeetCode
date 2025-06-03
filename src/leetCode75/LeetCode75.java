package leetCode75;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public static double findMaxAverage(int[] nums, int k) {
        if (nums.length == 0) return 0D;
        if (nums.length == 1) return nums[0];
        int maxAvg = 0;
        int s = 0;
        for (int i = 0; i < k; i++) {
            s += nums[i];
        }
        maxAvg = s;
        for (int i = k; i < nums.length; i++) {
            s = s - nums[i - k] + nums[i];
            if (maxAvg < s) maxAvg = s;
        }
        return (double) maxAvg / k;
    }

    public int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
        }};

        int max = 0;
        int cnt = 0;
        int l = s.length();
        for (int i = 0; i < k; i++) {
            if (vowels.contains(s.charAt(i))) {
                cnt++;
            }
        }
        max = cnt;

        for (int i = k; i < l; i++) {
            if (vowels.contains(s.charAt(i))) {
                cnt++;
            }
            if (vowels.contains(s.charAt(i - k))) {
                cnt--;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }

    public int maxVowels2(String s, int k) {
        int maxCount = 0;
        int[] arr = new int[26];
        arr['a' - 'a'] = arr['e' - 'a'] = arr['i' - 'a'] = arr['o' - 'a'] = arr['u' - 'a'] = 1;
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            maxCount += arr[c - 'a'];
        }

        int curr = maxCount;
        for (int i = k; i < s.length(); i++) {
            char c = s.charAt(i);
            curr += arr[c - 'a'];
            curr -= arr[s.charAt(i - k) - 'a'];
            maxCount = Math.max(curr, maxCount);
        }
        return maxCount;
    }

    public static int longestOnes(int[] nums, int k) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        int max = 0;
        int all = 0;
        int one = 0;
        int j = 0;
        for (int num : nums) {
            if (num == 1) {
                all++;
                one++;
            }
            if (num == 0) {
                if (k > 0) {
                    all -= memo.getOrDefault(j % k, 0);
                    memo.put(j % k, one + 1);
                    one = 0;
                    j++;
                    all++;
                } else all = 0;
            }
            max = Math.max(max, all);
        }
        return max;
    }

    public static int longestOnes2(int[] nums, int k) {
        int max = 0, start = 0;
        for (int end = 0; end < nums.length; end++) {
            k -= (1 - nums[end]);
            if (k < 0) {
                k += (1 - nums[start]);
                start++;
            } else max = Math.max(end - start + 1, max);
        }
        return max;
    }

    public int longestSubarray(int[] nums) {
        int max = 0, start = 0, k = 1;
        for (int end = 0; end < nums.length; end++) {
            k -= (1 - nums[end]);
            if (k < 0) {
                k += (1 - nums[start]);
                start++;
            } else max = Math.max(end - start + 1, max) - 1;
        }
        return max;
    }

    public int longestSubarray2(int[] nums) {
        int curr = 0, prev = 0, ans = 0, zeros = 0;
        for (int n : nums) {
            if (n == 0) {
                zeros++;
                ans = Math.max(ans, curr + prev);
                prev = curr;
                curr = 0;
            } else {
                curr++;
            }
        }
        ans = Math.max(ans, curr + prev);
        return (zeros == 0) ? ans - 1 : ans;

    }

    public static void main(String[] args) {
        System.out.println(longestOnes2(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }
}
