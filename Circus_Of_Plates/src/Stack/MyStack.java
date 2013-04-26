package Stack;

import java.io.Serializable;

import SLinkedList.*;

public class MyStack<type> extends MyLinkedListS<type> implements Serializable {
	private static Exception stackEmpty = new Exception("StackEmptyException");

	public boolean isEmpty() {
		return size() == 0;
	}

	public type peek() throws Exception {
		if (isEmpty())
			throw stackEmpty;
		else
			return get(0);
	}

	public type pop() throws Exception {
		if (isEmpty())
			throw stackEmpty;
		else {
			type temp = get(0);
			remove(0);
			return temp;
		}
	}

	public void push(type element) throws Exception {
		add(0, element);
	}

	public static void main(String[] args) throws Exception {

		MyStack<Integer> x = new MyStack<Integer>();
		x.push(321115);
		x.push(5);
		x.push(167);
		while (!x.isEmpty())
			System.out.println(x.pop());
		x.pop();
	}
}
