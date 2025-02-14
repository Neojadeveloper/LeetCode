package bfs;

public class SameTree {
    public static void main(String[] args) {
        TreeNode p = new TreeNode();
        TreeNode q = new TreeNode();
        System.out.println(isSameTree(p, q));
    }


    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q ==null) return true;
        if (p == null || q ==null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}


