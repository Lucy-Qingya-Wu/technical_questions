import java.util.*;

public class Chapter2 {
	
	// remove duplicates from an unsorted list
	public static NodeLinkedList removeDuplicate1(NodeLinkedList l){
		
		HashSet<Integer> hashset = new HashSet<Integer>();
		
		Node n = l.getHead();
		if (n == null){
			return l;
		}
		
		HashSet<Integer> hs = new HashSet<Integer>();
		
		while (n != null){
			if (hs.contains(n.value)){
				// if pre is null
				if (n.pre == null){
					n.next.pre = null;
				    l.setHead(n.next);
				}else{
					// if pre is NOT null	
					n.pre.next = n.next;
					if (n.next != null){
						n.next.pre = n.pre;
					}

				}
			}else{
				hs.add(n.value);
			}
			n = n.next;
		}
		
		return l;
	}
	
	public static NodeLinkedList removeDuplicate2(NodeLinkedList l){
		
		Node n = l.getHead();
		if (n == null){
			return l;
		}
		
		while (n != null){
			int value = n.value;
			Node start = n.next;
			while (start != null){
				if (start.value == value){
					start.pre.next = start.next;
					start.next.pre = start.pre;
				}
				start = start.next;
			}
			n = n.next;
		}
		
		return l;
		
		
	}
	
	public static Node returnKthToLast(NodeLinkedList l, int k){

	    Node n1 = null;
	    int count = 0;

	    while (count < k){
	      if (count == 0){
		n1 = l.getHead();
	      }else{
		n1 = n1.next;
	      }
	      if (n1 == null){
		System.out.println("length of linked list is shorted than what is expected");
		return null;
	      }
	      count += 1;
	    }


	    Node n2 = l.getHead();
	    while (n1.next != null){
	      n1 = n1.next;
	      n2 = n2.next;
	    }
	    return n2;
	}

	public static boolean deleteNodeInMiddle(Node n){

	    if (n == null || n.next == null){
	      return false;
	    }

	    n.setValue(n.next.value);
		n.next = n.next.next;
		if (n.next.next != null){
		  n.next.next.pre = n;
		}
	    return true;


	}

	public static void main(String[] args) {
		
	        HashSet<String> set = new HashSet<String>();
	        set.add("ABC");
	        if (set.contains("ABC")){
	         System.out.println("the set contains " + "ABC");
	        }		
		
		NodeLinkedList ll = new NodeLinkedList();
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(4);
		Node n6 = new Node(4);
		Node n7 = new Node(5);
		Node n8 = new Node(6);
		Node n9 = new Node(5);
		Node n10 = new Node(10);
		
		ll.append(n1);
		ll.append(n2);
		ll.append(n3);
		ll.append(n4);
		ll.append(n5);
		ll.append(n6);
		ll.append(n7);
		ll.append(n8);
		ll.append(n9);
		ll.append(n10);
		
    
		NodeLinkedList result = removeDuplicate1(ll);
		result.print();
		
		Node result = returnKthToLast(ll, 4);
		if (result != null){
		  System.out.println(result.value);
		}

		
		boolean result = deleteNodeInMiddle(n10);
		System.out.println(result);
		ll.print();
		
		
		
	}

}

class NodeLinkedList{
	private Node head;
	
	NodeLinkedList(){
		this.head = null;
	}
	
	public Node getHead(){
		return this.head;
	}
	
	public void setHead(Node n){
		this.head = n;
	}
	
	public void print(){
		Node n = this.head;
		
		while (n != null){
			System.out.println(n.value);
			n = n.next;
		}
	}
	
	public void append(Node newNode){
		Node n = this.head;
		if (n == null){
			this.setHead(newNode);
		}
		else{
			while (n.next != null){
				n = n.next;
			}
			n.next = newNode;
			newNode.pre = n;
		}
	}

	
}

class Node {
	public Node pre;
	public Node next;
	public int value;
	
	Node(int value){
		this.value = value;
		this.pre = null;
		this.next = null;
	}
	
}
