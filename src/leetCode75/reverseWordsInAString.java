package leetCode75;

public class reverseWordsInAString {
    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }

    public static String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        String[] split = s.split(" ");
        for (int i = split.length - 1; i >= 0; i--) {
            String strip = split[i].strip();
            if (!strip.isEmpty()) {
                builder.append(strip);
                builder.append(" ");
            }
        }
        return builder.toString().strip();
    }

    public static String reverseWords2(String s) {
        char[] ch = s.toCharArray();
        char[] r = new char[ch.length];
        int e = helper(ch, r, 0, 0);
        return new String(r, 0, e);
    }

    public static int helper(char[] ch, char[] r, int i, int l) {
        while (i < ch.length && ch[i] == ' ') i++;
        int e = i;
        while (e < ch.length && ch[e] != ' ') e++;
        if (i == e) return l;
        l = helper(ch, r, e + 1, l);

        if (l > 0) r[l++] = ' ';

        for (int j = i; j < e; j++) r[l++] = ch[j];
        return l;
    }
}
