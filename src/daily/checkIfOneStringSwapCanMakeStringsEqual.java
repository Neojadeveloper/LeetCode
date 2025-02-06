package daily;

public class checkIfOneStringSwapCanMakeStringsEqual {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        int count = 0;
        int[] diff = new int[2];
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (count == 2) return false;
                diff[count++] = i;
            }
        }
        return s1.charAt(diff[0]) == s2.charAt(diff[1]) && s1.charAt(diff[1]) == s2.charAt(diff[0]);
    }
    public boolean areAlmostEqual2(String s1, String s2) {
        int firstDiff = -1;
        int secondDiff = -1;
        for ( int i = 0; i < s1.length(); i++)
        {
            if ( s1.charAt(i) != s2.charAt(i))
            {
                if (firstDiff == -1)
                    firstDiff = i;
                else if ( secondDiff == -1)
                    secondDiff = i;
                else
                    return false;
            }
        }
        if ( firstDiff == -1 && secondDiff == -1)
            return true;
        if (firstDiff != -1 && secondDiff == -1 )
            return false;
        if (secondDiff != -1 && firstDiff == -1 )
            return false;

        return s1.charAt(secondDiff) == s2.charAt(firstDiff) && s1.charAt(firstDiff) == s2.charAt(secondDiff);
    }
}
