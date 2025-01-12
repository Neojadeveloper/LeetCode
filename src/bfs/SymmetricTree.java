package bfs;

import java.util.ArrayList;

public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        ArrayList<Integer> nums = new ArrayList<>(){{
            add(1); add(2); add(2); add(null); add(3); add(null); add(3);
        }};
        int [] arr = new int[]{1,2,2,3,4,4,3,3,3,3,3,3,3,3,3,3,3,};
        root = root.arrayToTree(nums);
        root = root.arrayToTree(arr);
        root.toStringPretty(root, "src/bfs/tree.mmd");
        System.out.println(isSymmetric(root));
    }


    public static boolean isSymmetric(TreeNode root) {
        return true;
    }
}


