package linked_list;

import java.util.HashSet;

// 两个单链表相交的一系列问题
// 【题目】给定两个可能有环也可能无环的单链表，头节点head1和head2。
// 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null
// 【要求】如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
public class C07_FindFirstIntersectNode {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// public static Node getIntersectNode(Node head1, Node head2) {
	// if (head1 == null || head2 == null) {
	// return null;
	// }
	// Node loop1 = getLoopNode(head1);
	// Node loop2 = getLoopNode(head2);
	// if (loop1 == null && loop2 == null) {
	// return noLoop(head1, head2);
	// }
	// if (loop1 != null && loop2 != null) {
	// return bothLoop(head1, loop1, head2, loop2);
	// }
	// return null;
	// }

	// public static Node getLoopNode(Node head) {
	// if (head == null || head.next == null || head.next.next == null) {
	// return null;
	// }
	// Node n1 = head.next; // n1 -> slow
	// Node n2 = head.next.next; // n2 -> fast
	// while (n1 != n2) {
	// if (n2.next == null || n2.next.next == null) {
	// return null;
	// }
	// n2 = n2.next.next;
	// n1 = n1.next;
	// }
	// n2 = head; // n2 -> walk again from head
	// while (n1 != n2) {
	// n1 = n1.next;
	// n2 = n2.next;
	// }
	// return n1;
	// }

	// public static Node noLoop(Node head1, Node head2) {
	// if (head1 == null || head2 == null) {
	// return null;
	// }
	// Node cur1 = head1;
	// Node cur2 = head2;
	// int n = 0;
	// while (cur1.next != null) {
	// n++;
	// cur1 = cur1.next;
	// }
	// while (cur2.next != null) {
	// n--;
	// cur2 = cur2.next;
	// }
	// if (cur1 != cur2) {
	// return null;
	// }
	// cur1 = n > 0 ? head1 : head2;
	// cur2 = cur1 == head1 ? head2 : head1;
	// n = Math.abs(n);
	// while (n != 0) {
	// n--;
	// cur1 = cur1.next;
	// }
	// while (cur1 != cur2) {
	// cur1 = cur1.next;
	// cur2 = cur2.next;
	// }
	// return cur1;
	// }

	// public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
	// Node cur1 = null;
	// Node cur2 = null;
	// if (loop1 == loop2) {
	// cur1 = head1;
	// cur2 = head2;
	// int n = 0;
	// while (cur1 != loop1) {
	// n++;
	// cur1 = cur1.next;
	// }
	// while (cur2 != loop2) {
	// n--;
	// cur2 = cur2.next;
	// }
	// cur1 = n > 0 ? head1 : head2;
	// cur2 = cur1 == head1 ? head2 : head1;
	// n = Math.abs(n);
	// while (n != 0) {
	// n--;
	// cur1 = cur1.next;
	// }
	// while (cur1 != cur2) {
	// cur1 = cur1.next;
	// cur2 = cur2.next;
	// }
	// return cur1;
	// } else {
	// cur1 = loop1.next;
	// while (cur1 != loop1) {
	// if (cur1 == loop2) {
	// return loop1;
	// }
	// cur1 = cur1.next;
	// }
	// return null;
	// }
	// }

	// NOTE: 分类讨论
	public static Node getIntersectNode(Node head1, Node head2) {
		Node loopStart1 = getStartLoopNode(head1);
		Node loopStart2 = getStartLoopNode(head2);

		if (loopStart1 == null && loopStart2 == null) {
			return noLoop(head1, null, head2, null);
		} else if (loopStart1 != null && loopStart2 != null) {
			return bothLoop(head1, loopStart1, head2, loopStart2);
		}

		return null;
	}

	private static Node getStartLoopNode(Node head) {
		// 法一：使用HashSet
		HashSet<Node> tem = new HashSet<>();
		while (head != null) {
			if (tem.contains(head)) {
				return head;
			} else {
				tem.add(head);
				head = head.next;
			}
		}
		return null;

		// // 法二：使用快慢指针
		// if (head == null || head.next == null) {
		// return null;
		// }
		// Node quick = head.next.next;
		// Node slow = head.next;
		// while (quick != null && quick != slow) {
		// if (quick.next == null || quick.next.next == null){
		// return null;
		// }
		// quick = quick.next.next;
		// slow = slow.next;
		// }

		// quick = head;
		// while (quick != slow) {
		// quick = quick.next;
		// slow = slow.next;
		// }

		// return quick;
	}

	private static Node noLoop(Node head1, Node end1, Node head2, Node end2) {
		end1 = end1 == null ? end1 : end1.next; // 防止"链表结尾" end.next出现空指针
		end2 = end2 == null ? end2 : end2.next;

		// // 法一：利用HashSet;
		// HashSet<Node> tem = new HashSet<>();
		// while (head1 != end1.next ) {
		// tem.add(head1);
		// head1 = head1.next;
		// }

		// while (head2 != end2.next) {
		// if (tem.contains(head2)) {
		// return head2;
		// } else {
		// head2 = head2.next;
		// }
		// }
		// return null;

		// 法二：
		if (head1 == end1 || head2 == end2) {
			return null;
		}
		Node longer = head1;
		Node shorter = head2;
		int head1Length = 0;
		int head2Length = 0;
		while (longer.next != end1) {
			head1Length++;
			longer = longer.next;
		}
		while (shorter.next != end2) {
			head2Length++;
			shorter = shorter.next;
		}
		head1Length++;
		head2Length++;
		if (longer != shorter) {
			return null;
		}

		longer = head1Length > head2Length ? head1 : head2;
		shorter = head1Length <= head2Length ? head1 : head2;

		int distance = Math.abs(head1Length - head2Length);
		while (distance-- > 0) {
			longer = longer.next;
		}

		while (longer != shorter) {
			longer = longer.next;
			shorter = shorter.next;
		}
		return longer;
	}

	private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		Node tem;
		if (loop1 == loop2) {
			return noLoop(head1, loop1, head2, loop2);
		} else {
			tem = loop1.next;
			while (tem != loop1) {
				if (tem == loop2) {
					return loop1;
				} else {
					tem = tem.next;
				}
			}
			return null;
		}
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}
}
