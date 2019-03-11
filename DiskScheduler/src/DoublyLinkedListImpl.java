import java.util.NoSuchElementException;

/**
 * 
 * @author http://www.java2novice.com/data-structures-in-java/linked-list/doubly-linked-list/ and nlarsen
 *
 * @param <E>
 */
public class DoublyLinkedListImpl<E> {

	private Node head;
	private Node tail;
	private Node current;
	private int size;

	public DoublyLinkedListImpl() {
		size = 0;
	}
	/**
	 * this class keeps track of each element information
	 * @author java2novice
	 *
	 */
	private class Node {
		E element;
		Node next;
		Node prev;

		public Node(E element, Node next, Node prev) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
	}
	/**
	 * returns the size of the linked list
	 * @return
	 */
	public int size() { return size; }

	/**
	 * return whether the list is empty or not
	 * @return
	 * @author nlarsen
	 */
	public boolean isEmpty() { return size == 0; }

	/**
	 * Returns whether the current is the head
	 * @return
	 * @author nlarsen
	 */
	public boolean isHead() {
		if(current == head) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * adds element at the starting of the linked list
	 * @param element
	 */
	public void addFirst(E element) {
		Node tmp = new Node(element, head, null);
		if(head != null ) {head.prev = tmp;}
		head = tmp;
		if(tail == null) { tail = tmp;}
		size++;
	}

	/**
	 * adds element at the end of the linked list
	 * @param element
	 */
	public void addLast(E element) {

		Node tmp = new Node(element, null, tail);
		if(tail != null) {tail.next = tmp;}
		tail = tmp;
		if(head == null) { head = tmp;}
		size++;
	}

	/**
	 * Sets the head as the current node
	 * @author nlarsen
	 */
	public void setCurrentAsHead() {
		if(size ==0) throw new NoSuchElementException();
		current = head;
	}

	/**
	 * this method walks forward through the linked list
	 */
	public void iterateForward(){

		System.out.println("iterating forward..");
		Node tmp = head;
		while(tmp != null){
			System.out.println(tmp.element);
			tmp = tmp.next;
		}
	}



	/**
	 * this method walks backward through the linked list
	 */
	public void iterateBackward(){

		System.out.println("iterating backward..");
		Node tmp = tail;
		while(tmp != null){
			System.out.println(tmp.element);
			tmp = tmp.prev;
		}
	}

	/**
	 * Returns true if the current node is the head.
	 * @return
	 * @author nlarsen
	 */
	public boolean checkIsHead() {
		if(current == head) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Returns true if the current node is the tail.
	 * @return
	 * @author nlarsen
	 */
	public boolean checkIsTail() {
		if(current == tail) {
			boolean test = current==tail;
			System.out.println("current == tail is " + test);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * This method walks forward one node
	 * @author nlarsen
	 */
	public void moveForwardOne() {
		if(current != null){
			System.out.print("moving forward one node. Now at ");
			current = current.next;
			System.out.println(current.element);
		} else {
			System.out.println("Cannot move forward");
		}
	}

	/**
	 * This method walks backward one node
	 * @author nlarsen
	 */
	public void moveBackwardOne() {
		if(current != null){
			System.out.print("moving backward one node. Now at ");
			current = current.prev;
			System.out.println(current.element);
		} else {
			System.out.println("Cannot move backward");
		}
	}

	/**
	 * this method removes element from the start of the linked list
	 * @return
	 */
	public E removeFirst() {
		if (size == 0) throw new NoSuchElementException();
		Node tmp = head;
		head = head.next;
		head.prev = null;
		size--;
		System.out.println("deleted: "+tmp.element);
		return tmp.element;
	}

	/**
	 * this method removes element from the end of the linked list
	 * @return
	 */
	public E removeLast() {
		if (size == 0) throw new NoSuchElementException();
		Node tmp = tail;
		tail = tail.prev;
		tail.next = null;
		size--;
		System.out.println("deleted: "+tmp.element);
		return tmp.element;
	}

	/**
	 * Removes the current node. The new current node is the previous node to the old current, unless the current is the head, in which case the new current will be the head
	 * @return
	 * @author nlarsen
	 */
	public E removeCurrent() {
		if(size ==0) throw new NoSuchElementException();
		if(size == 1) {
			Node tmp = current;
			current = null;
			tail = null;
			head = null;
			size--;
			return tmp.element;
		}else if(current == tail && current != head) {
			Node tmp = tail;
			tail = tail.prev;
			tail.next = null;
			current = tail;
			size--;
			return tmp.element;
		}else if(current != tail && current == head) {
			Node tmp = head;
			head = head.next;
			head.prev = null;
			current = head;
			size--;
			return tmp.element;
		}else {
			//Node previousNode = current.prev;
			//Node nextNode = current.next;
			Node tmp = current;

			//System.out.println(current.prev.next.element);

			if(current == head) {
//				System.out.println("current 1 " + current.element);
				current = current.prev;
//				System.out.println("current 2 " + current.element);
				current.next = current.next.next;
//				System.out.println("current 3 " + current.element);
				current = current.next;
//				System.out.println("current 4 " + current.element);
				current.prev = current.prev.prev;
//				System.out.println("current 5 " + current.element + " " +current.prev.element);
				current = current.prev;
				size--;
			}
			else if(current == tail) {
//				System.out.println("current 1 " + current.element);
				current = current.prev;
//				System.out.println("current 2 " + current.element);
				current.next = current.next.next;
//				System.out.println("current 3 " + current.element);
				current = current.next;
//				System.out.println("current 4 " + current.element);
				current.prev = current.prev.prev;
//				System.out.println("current 5 " + current.element + " " +current.prev.element);
				current = current.prev;
				current = tail;
				size--;
			} else {
//				System.out.println("current 1 " + current.element);
				current = current.prev;
//				System.out.println("current 2 " + current.element);
				current.next = current.next.next;
//				System.out.println("current 3 " + current.element);
				current = current.next;
//				System.out.println("current 4 " + current.element);
				current.prev = current.prev.prev;
//				System.out.println("current 5 " + current.element + " " +current.prev.element);
				current = current.prev;
				size--;
			}
			System.out.println();
			return tmp.element;
		}
	}
	
	/**
	 * This returns the current element
	 * @author nlarsen
	 * @return
	 */
	public E getCurrentElement() {
		if(size ==0) throw new NoSuchElementException();
		return current.element;
	}
	
	/**
	 * Returns the next element
	 * @return
	 * @author nlarsen
	 */
	public E getNextElement() {
		if(size == 0) throw new NoSuchElementException();
		return current.next.element;
	}

}
