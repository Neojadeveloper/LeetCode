package leetCode75;

import util.Util;

public class StringCompression {
    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
    }

    public static int compress(char[] chars) {
        int n = chars.length;
        int write = 0, read = 0;

        while (read < n) {
            char currentChar = chars[read];
            int count = 0;

            // Shu belgidan nechta borligini sanaymiz
            while (read < n && chars[read] == currentChar) {
                read++;
                count++;
            }

            // Belgini yozamiz
            chars[write++] = currentChar;

            // Agar takrorlanish bo‘lsa, sonni ham yozamiz (masalan '2', '3', ...)
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[write++] = c;
                }
            }
        }
        System.out.println(Util.array2String(chars));
        return write;
    }

    private int roomCount(int cnt) {
        if (cnt / 10 == 0)
            return 0;
        return 1 + roomCount(cnt / 10);
    }
}
