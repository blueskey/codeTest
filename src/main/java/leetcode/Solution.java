package leetcode;

import java.util.List;

/**
 * Created by Administrator on 2018/11/19.
 */
public class Solution {

	public List<List<Integer>> levelOrder(TreeNode root) {
		while (true) {

		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode root1 = new TreeNode(9);
		TreeNode root2 = new TreeNode(20);
		TreeNode root3 = new TreeNode(15);
		TreeNode root4 = new TreeNode(7);
		root.left = root1;
		root.right = root2;
		root2.left = root3;
		root2.right = root4;
		System.out.println();
	}

}


class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

