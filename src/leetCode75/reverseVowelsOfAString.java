package leetCode75;

import java.util.HashSet;
import java.util.Set;

public class reverseVowelsOfAString {
    public static void main(String[] args) {
        System.out.println(reverseVowels("IceCreAm"));
        System.out.println(reverseVowels2("IceCreAm"));
    }

    public static String reverseVowels(String s) {
        StringBuilder b = new StringBuilder();
        StringBuilder r = new StringBuilder();
        for (int length = s.length() - 1; length >= 0; length--) {
            char ch = s.charAt(length);
            if ('a' == ch || 'e' == ch || 'i' == ch || 'o' == ch || 'u' == ch || 'A' == ch || 'E' == ch || 'I' == ch || 'O' == ch || 'U' == ch) {
                b.append(ch);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ('a' == ch || 'e' == ch || 'i' == ch || 'o' == ch || 'u' == ch || 'A' == ch || 'E' == ch || 'I' == ch || 'O' == ch || 'U' == ch) {
                r.append(b.substring(0, 1));
                b.delete(0, 1);
            } else r.append(ch);
        }
        return r.toString();
    }

    public static String reverseVowels2(String s) {
        Set<Character> vowels = new HashSet<>();
        for (char c : "aeiouAEIOU".toCharArray()) vowels.add(c);

        char[] arr = s.toCharArray();
        int left = 0, right = s.length() - 1;

        while (left < right) {
            while (left < right && !vowels.contains(arr[left])) left++;
            while (left < right && !vowels.contains(arr[right])) right--;

            // Swap vowels
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return new String(arr);
    }
}
