package leetCode75;

public class mergeStringsAlternately {
    public static void main(String[] args) {
        mergeAlternately("abc", "pqr");
    }

    public static String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int l = Math.min(word1.length(), word2.length());
        for (int i = 0; i < l; i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }
        if (word1.length() > word2.length()) {
            sb.append(word1.substring(l));
        } else {
            sb.append(word2.substring(l));
        }
        return sb.toString();
    }
}
