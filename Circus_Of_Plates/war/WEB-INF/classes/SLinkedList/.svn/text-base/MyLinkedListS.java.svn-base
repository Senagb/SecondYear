package SLinkedList;

public class MyLinkedListS<type>
{
	private Node<type> header;
	private Node<type> tail;
	private int length;
	private Exception invalidIndex;

	public MyLinkedListS()
	{
		invalidIndex = new Exception("InvalidIndexException");
		header = new Node<type>(null, null);
		tail = header;
		length = 0;
	}

	public void set(int index, type element) throws Exception
	{
		if (index < 0 || index >= length)
			throw (invalidIndex);
		else
		{
			Node<type> current = header;
			for (int i = 0; i <= index; i++)
			{
				current = current.getNext();
			}
			current.setElement(element);
		}
	}

	public void add(int index, type element) throws Exception
	{
		if (index < 0 || index > length) // if it's equals length it will be
											// automatically inserted at tail
			throw (invalidIndex);
		else
		{
			Node<type> current = header;
			for (int i = 0; i < index; i++)
			{
				current = current.getNext();
			}
			Node<type> nNode = new Node<type>(element, current.getNext());
			current.setNext(nNode);
			length++;
		}
	}

	/**
	 * a helper method that returns the Node at the specified index used by
	 * sublist method
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	private Node<type> getNode(int index) throws Exception
	{
		if (index < 0 || index >= length)
			throw (invalidIndex);
		Node<type> current = header;
		for (int i = 0; i <= index; i++)
		{
			if (current != null)
				current = current.getNext();
		}

		return current;
	}

	/**
	 * returns the object at the specified index
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public type get(int index) throws Exception
	{
		if (index < 0 || index >= length)
			throw (invalidIndex);
		Node<type> current = header;
		for (int i = 0; i <= index; i++)
		{
			current = current.getNext();
		}

		return current.getElement();
	}

	/**
	 * inserts given object at the tail of the list
	 * 
	 * @param element
	 */
	public void add(type element)
	{
		Node<type> nTail = new Node<type>(element, null);
		this.tail.setNext(nTail);
		tail = nTail;
		length++;

	}

	/**
	 * clears the list
	 */
	public void clear()
	{
		tail = header;
		header.setNext(null);
		length = 0;
		// System.gc();
	}

	/**
	 * @return true if the list is empty
	 */
	public boolean isEmpty()
	{
		return length == 0;
	}

	/**
	 * 
	 * @return the size of the list
	 */
	public int size()
	{
		return length;
	}

	/**
	 * removes the Node at the specified index
	 * 
	 * @param index
	 * @throws Exception
	 */
	public void remove(int index) throws Exception
	{
		if (index < 0 || index >= length)
			throw (invalidIndex);
		else
		{
			Node<type> current = header;
			for (int i = 0; i < index; i++)
			{
				current = current.getNext();
			}
			Node<type> removed = current.getNext();
			current.setNext(current.getNext().getNext());
			removed.clearNode();
			length--;
			if (length == 0)
				tail = header;
		}

	}

	/**
	 * 
	 * @param element
	 * @return returns true is an object in the list has the same value as
	 *         element
	 */
	public boolean contains(type element)
	{
		Node<type> n = header.getNext();
		boolean found = false;
		while (n != null && !found)
		{
			if (element.equals(n.getElement()))
				found = true;
			n = n.getNext();

		}
		return found;
	}

	/**
	 * returns a preview of the list from fromIndex to toIndex (INCLUSIVE)
	 * 
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 * @throws Exception
	 */
	public MyLinkedListS<type> sublist(int fromIndex, int toIndex)
			throws Exception
	{
		if (fromIndex < 0 || toIndex < 0 || fromIndex >= length
				|| toIndex >= length)
			throw (invalidIndex);
		else if (toIndex < fromIndex)
		{
			throw (new Exception("toIndexMustBeGreaterThanfromIndexException"));
		} else
		{
			MyLinkedListS<type> sublist = new MyLinkedListS<type>();
			Node<type> current = getNode(fromIndex);
			for (int i = 0; i <= toIndex - fromIndex; i++)
			{
				sublist.add(current.getElement());
				current = current.getNext();
			}

			return sublist;
		}
	}

	/**
	 * changes the list to a string **Note : this will only work if the objects
	 * in the list have toString methods too , else it will print references to
	 * those objects
	 */
	public String toString()
	{
		String s = "[ ";
		Node<type> current = header.getNext();
		while (current != null)
		{
			s = s + current.getElement() + " ";

			current = current.getNext();
		}
		s = s + "]";
		return s;
	}

	/**
	 * 
	 * @return an iterator of the list
	 */
	public MyLLIteratorS<type> getIterator()
	{
		return new MyLLIteratorS<type>(header);
	}

	public static void main(String[] args) throws Exception
	{
	}

}
