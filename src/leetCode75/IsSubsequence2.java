package leetCode75;

public class IsSubsequence2 {
    public static void main(String[] args) {
        String s = "as";
        String t = "qweqwe";
        extracted(t, s);
    }

    private static boolean extracted(String t, String s) {
        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
                if (j == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }
}
