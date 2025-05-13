package util;

public class Util {
    public static String array2String(int[] array) {
        StringBuilder res = new StringBuilder("[");
        for (int i : array) {
            res.append(i).append(",");
        }
        return res + "]";
    }
}
