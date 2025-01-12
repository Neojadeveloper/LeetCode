package bfs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TreeNode {
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

    // Convert ArrayList to tree
    // Convert ArrayList to tree, handling null values to stop constructing subtrees
    public TreeNode arrayToTree(ArrayList<Integer> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return arrayListToTreeHelper(list, 0);
    }

    // Helper method to recursively build the tree from the ArrayList
    private TreeNode arrayListToTreeHelper(ArrayList<Integer> list, int index) {
        // If the index is out of bounds or the current value is null, stop the recursion
        if (index >= list.size() || list.get(index) == null) {
            return null;
        }

        // Create the current node
        TreeNode node = new TreeNode(list.get(index));

        // Recursively build the left and right subtrees
        node.left = arrayListToTreeHelper(list, 2 * index + 1); // Left child is at 2 * index + 1
        node.right = arrayListToTreeHelper(list, 2 * index + 2); // Right child is at 2 * index + 2

        return node;
    }

    public TreeNode arrayToTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return arrayToTreeHelper(arr, 0);
    }

    private TreeNode arrayToTreeHelper(int[] arr, int index) {
        if (index >= arr.length) {
            return null;
        }

        TreeNode node = new TreeNode(arr[index]);

        node.left = arrayToTreeHelper(arr, 2 * index + 1);
        node.right = arrayToTreeHelper(arr, 2 * index + 2);

        return node;
    }

    // Method to generate a pretty Mermaid string representation of the tree and save it to a file
    public void toStringPretty(TreeNode node, String fileName) {
        if (node == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("graph TD\n");
        buildMermaidDiagram(node, sb, "A");  // "A" is the root node identifier
//        System.out.println(sb);
        // Write the output to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(sb.toString());
            System.out.println("Mermaid diagram has been saved to " + fileName);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Recursive helper method to build the Mermaid diagram from the tree
    private void buildMermaidDiagram(TreeNode node, StringBuilder sb, String parentNode) {
        if (node == null) {
            return;
        }

        // Add the current node to the Mermaid diagram
        sb.append("\t").append(parentNode).append("((").append(node.val).append("))\n");

        // Handle left and right children (whether they are null or not)
        if (node.left ==null && node.right == null) return;
        addChildNode(node.left, sb, parentNode, "L");
        addChildNode(node.right, sb, parentNode, "R");
    }

    // Helper method to add child nodes (including handling null values)
    private void addChildNode(TreeNode child, StringBuilder sb, String parentNode, String childLabel) {
        String childNode = parentNode + childLabel;

        // If the child is not null, recursively build the tree; otherwise, represent null
        if (child != null) {
            sb.append("\t").append(parentNode).append(" --- ").append(childNode).append("\n");
            buildMermaidDiagram(child, sb, childNode);  // Recursively add the child node
        } else {
            sb.append("\t").append(parentNode).append(" --- ").append(childNode).append("\n");
            sb.append("\t").append(childNode).append("((").append(" ").append("))\n");
        }
    }

}
