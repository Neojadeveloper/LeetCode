package leetCode75;


import java.util.*;

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

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            set2.add(i);
        }
        for (int i : nums1) {
            set2.remove(i);
        }
        for (int i : nums2) {
            set1.remove(i);
        }
        List<List<Integer>> r = new ArrayList<>();
        r.add(new ArrayList<>(set1));
        r.add(new ArrayList<>(set2));
        return r;
    }

    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (Integer value : map.values()) {
            int orDefault = map2.getOrDefault(value, 0) + 1;
            if (orDefault == 2) return false;
            map2.put(value, orDefault);
        }
        return true;
    }

    public static boolean uniqueOccurrences2(int[] arr) {
        int[] first = new int[2001];
        for (int i : arr) {
            first[i + 1000]++;
        }
        boolean[] second = new boolean[1001];
        for (int i : arr) {
            int j = first[i + 1000];
            first[i + 1000] = 0;
            if (j > 0 && second[j]) return false;
            second[j] = true;
        }
        return true;
    }

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : word1.toCharArray()) {
            freq1[c - 'a']++;
            set1.add(c);
        }

        for (char c : word2.toCharArray()) {
            freq2[c - 'a']++;
            set2.add(c);
        }

        if (!set1.equals(set2)) return false;

        Arrays.sort(freq1);
        Arrays.sort(freq2);

        return Arrays.equals(freq1, freq2);
    }

    public boolean closeStrings2(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        if (word2.equals(word1)) return true;


        int[] freq1 = new int['z' + 1];
        int[] freq2 = new int['z' + 1];

        for (char c : word1.toCharArray()) {
            freq1[c]++;
        }

        for (char c : word2.toCharArray()) {
            freq2[c]++;
        }
        int maxFreq = 0;
        for (int i = 'a'; i <= 'z'; i++) {
            maxFreq = Math.max(maxFreq, Math.max(freq1[i], freq2[i]));
        }
        byte[] freqCounter = new byte[maxFreq + 1];
        int unmatchedCount = 0;
        for (int i = 'a'; i <= 'z'; i++) {
            int f1 = freq1[i];//2
            int f2 = freq2[i];//3
            if ((f1 == 0) ^ (f2 == 0)) return false;
            if (f1 != 0) {
                int countBeforeF1 = freqCounter[f1]++;
                int countBeforeF2 = freqCounter[f2]--;

                if (countBeforeF1 == 0) unmatchedCount++;
                else if (countBeforeF1 == -1) unmatchedCount--;

                if (countBeforeF2 == 0) unmatchedCount++;
                else if (countBeforeF2 == 1) unmatchedCount--;
            }
        }

        return unmatchedCount == 0;
    }

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> rowMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]).append(",");
            }
            rowMap.put(sb.toString(), rowMap.getOrDefault(sb.toString(), 0) + 1);
        }

        int count = 0;
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(grid[i][j]).append(",");
            }
            count += rowMap.getOrDefault(sb.toString(), 0);
        }
        return count;
    }

    public int equalPairs2(int[][] grid) {
        int n = grid.length;
        Map<List<Integer>, Integer> rowMap = new HashMap<>();
        for (int[] ints : grid) {
            List<Integer> rowList = new ArrayList<>();
            for (int i : ints) {
                rowList.add(i);
            }
            rowMap.put(rowList, rowMap.getOrDefault(rowList, 0) + 1);
        }

        int count = 0;
        for (int j = 0; j < n; j++) {
            List<Integer> col = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                col.add(grid[i][j]);
            }
            count += rowMap.getOrDefault(col, 0);
        }
        return count;
    }

    public String removeStars(String s) {
        Stack<Character> chars = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') chars.pop();
            else chars.add(c);

        }
        for (Character aChar : chars) {
            result.append(aChar);
        }
        return result.toString();
    }

    public String removeStars2(String s) {
        char[] charArray = s.toCharArray();
        int i = 0;
        for (char c : charArray) {
            if (c == '*') i--;
            else charArray[i++] = c;
        }
        return new String(charArray, 0, i);
    }

    public static int[] asteroidCollision(int[] asteroids) {
        int w = -1;
        for (int i = 0; i < asteroids.length; i++) {
            boolean destroyed = false;
            if (asteroids[i] > 0) asteroids[++w] = asteroids[i];
            else {
                while (w >= 0 && asteroids[w] > 0) {
                    if (asteroids[w] < -asteroids[i]) w--;
                    else if (asteroids[w] == -asteroids[i]) {
                        w--;
                        destroyed = true;
                        break;
                    } else {
                        destroyed = true;
                        break;
                    }
                }
                if (!destroyed) asteroids[++w] = asteroids[i];
            }
        }
        return Arrays.copyOfRange(asteroids, 0, w + 1);
    }

    public static int[] asteroidCollision2(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            boolean destroyed = false;
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (stack.peek() < -asteroid) {
                    stack.pop();
                } else if (stack.peek() == -asteroid) {
                    stack.pop();
                    destroyed = true;
                    break;
                } else {
                    destroyed = true;
                    break;
                }
            }
            if (!destroyed) {
                stack.push(asteroid);
            }
        }
        int s = stack.size();
        int[] res = new int[s];
        for (int i = s - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    public static String decodeString(String s) {
        Stack<Integer> counts = new Stack<>();
        Stack<StringBuilder> result = new Stack<>();
        StringBuilder current = new StringBuilder();
        int num = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                counts.push(num);
                result.push(current);
                current = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                StringBuilder temp = current;
                current = result.pop();
                int count = counts.pop();
                current.append(String.valueOf(temp).repeat(Math.max(0, count)));
            } else {
                current.append(c);
            }
        }
        return current.toString();
    }

    static class RecentCounter {
        private final Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (Objects.nonNull(queue.peek()) && queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        int l = senate.length();
        for (int i = 0; i < l; i++) {
            if (senate.charAt(i) == 'R') radiant.offer(i);
            else dire.offer(i);
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            Integer rIdx = radiant.poll();
            Integer dIdx = dire.poll();
            if (rIdx < dIdx) radiant.offer(rIdx + l);
            else dire.offer(dIdx + l);
        }
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head, fast = head, prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = slow.next; // Remove the middle node

        return head;
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode odd = head, even = head.next, evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next; // Link odd nodes
            even.next = even.next.next; // Link even nodes
            odd = odd.next; // Move to next odd node
            even = even.next; // Move to next even node
        }

        odd.next = evenHead; // Connect the end of odd list to the head of even list

        return head;
    }

    public ListNode reverseList(ListNode head) {
        ListNode reverse = null, curr;
        while (head != null) {
            curr = head;
            head = head.next;
            reverse = new ListNode(curr.val, reverse);
        }
        return reverse;
    }

    public static int pairSum(ListNode head) {
        int max = 0;
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current.val);
            current = current.next;
            i++;
        }
        for (int j = 0; j < i / 2; j++) {
            max = Math.max(max, head.val + stack.pop());
            head = head.next;
        }

        return max;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> memo1 = new ArrayList<>();
        ArrayList<Integer> memo2 = new ArrayList<>();
        leafSimilar(root1, memo1);
        leafSimilar(root2, memo2);
        int size1 = memo1.size();
        int size2 = memo2.size();
        if (size1 != size2) return false;
        for (int i = 0; i < size2; i++) {
            if (!Objects.equals(memo2.get(i), memo1.get(i))) return false;
        }
        return true;
    }

    public void leafSimilar(TreeNode root, ArrayList<Integer> memo) {
        if (root.left == null && root.right == null) {
            memo.add(root.val);
            return;
        }
        if (root.left != null) leafSimilar(root.left, memo);
        if (root.right != null) leafSimilar(root.right, memo);
    }

    public static class TreeBuilder {
        public static TreeNode buildTree(Integer[] arr) {
            if (arr.length == 0 || arr[0] == null) return null;

            TreeNode root = new TreeNode(arr[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int i = 1;

            while (i < arr.length) {
                TreeNode current = queue.poll();
                if (arr[i] != null) {
                    assert current != null;
                    current.left = new TreeNode(arr[i]);
                    queue.offer(current.left);
                }
                i++;
                if (i < arr.length && arr[i] != null) {
                    assert current != null;
                    current.right = new TreeNode(arr[i]);
                    queue.offer(current.right);
                }
                i++;
            }
            return root;
        }
    }

    public static int goodNodes(TreeNode root) {
        return goodNodes(root, root.val);
    }

    private static int goodNodes(TreeNode root, int top) {
        if (root == null) return 0;

        int val = root.val;
        int count = 0;
        if (val >= top) {
            count++;
        }
        top = Math.max(top, val);
        count += goodNodes(root.left, top);
        count += goodNodes(root.right, top);
        return count;
    }

    public static int pathSum(TreeNode root, int targetSum) {
        return pathSum(root, targetSum, new HashMap<>(), 0);
    }

    private static int pathSum(TreeNode root, int targetSum, HashMap<Long, Integer> map, long sum) {
        if (root == null) return 0;
        int count = 0;
        sum += root.val;
        if (sum == targetSum) count++;
        count += map.getOrDefault(sum - targetSum, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        count += pathSum(root.left, targetSum, map, sum);
        count += pathSum(root.right, targetSum, map, sum);
        map.put(sum, map.get(sum) - 1);
        return count;
    }


    public static void main(String[] args) {
        Integer[] input = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root = TreeBuilder.buildTree(input);
        System.out.println(pathSum(root, 8));
    }
}
