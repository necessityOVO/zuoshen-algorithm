package skill;

import java.util.LinkedList;
import java.util.List;

// 题目
// 给定一个非负整数n，代表二叉树的节点个数。返回能形成多少种不同的二叉树结构
public class C06_UniqueBST {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 暴力递归
	public static int process(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}

		int res = 0;
		for (int leftNum = 0; leftNum <= n - 1; leftNum++) {
			int leftWays = process(leftNum);
			int rightWays = process(n - 1 - leftNum);
			res += leftWays * rightWays;
		}
		return res;
	}
	// TODO:法二：记忆化搜索

	// 动态规划
	public static int numTrees(int n) {
		if (n < 2) {
			return 1;
		}
		int[] num = new int[n + 1];
		num[0] = 1;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < i + 1; j++) {
				num[i] += num[j - 1] * num[i - j];
			}
		}
		return num[n];
	}

	// for test
	public static List<Node> generateTrees(int n) {
		return generate(1, n);
	}

	// for test
	public static List<Node> generate(int start, int end) {
		List<Node> res = new LinkedList<Node>();
		if (start > end) {
			res.add(null);
		}
		Node head = null;
		for (int i = start; i < end + 1; i++) {
			head = new Node(i);
			List<Node> lSubs = generate(start, i - 1);
			List<Node> rSubs = generate(i + 1, end);
			for (Node l : lSubs) {
				for (Node r : rSubs) {
					head.left = l;
					head.right = r;
					res.add(cloneTree(head));
				}
			}
		}
		return res;
	}

	// for test
	public static Node cloneTree(Node head) {
		if (head == null) {
			return null;
		}
		Node res = new Node(head.value);
		res.left = cloneTree(head.left);
		res.right = cloneTree(head.right);
		return res;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	// for test
	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	// for test
	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		int n = 4;
		System.out.println(numTrees(n));
		List<Node> res = generateTrees(n);
		for (Node node : res) {
			printTree(node);
		}
	}

}
