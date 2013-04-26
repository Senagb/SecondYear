package bufmgr;

import java.util.LinkedList;

/**
 * Queue datastructuer using linkedlist
 * */
public class Queue {

	LinkedList<Integer> l = null;

	// default constructor
	public Queue() {
		l = new LinkedList();
	}

	// return size of the list
	public int size() {
		return l.size();
	}

	// enqueue object o
	public void enqueue(Integer o) {
		if (!l.contains(o)) {
			l.addLast(o);
		}

	}

	// check if this element is found already in the queue since this case is
	// valid in our situation
	private boolean contain(Integer o) {
		if (l.contains(o)) {
			return true;
		} else {
			return false;
		}
	}

	// remove from the queue
	public int dequeue() {
		int temp = l.removeFirst();
		return temp;
	}

	// remove from the list certain element
	public void remove(Integer t) {
		l.remove(t);
	}

	// check if is empty or not
	public boolean isEmpty() {
		if (l.isEmpty()) {
			return true;
		} else {
			return false;
		}
	} 

}
