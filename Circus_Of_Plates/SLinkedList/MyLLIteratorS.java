package SLinkedList;

public class MyLLIteratorS<type>
{
	private Node<type> current;
	private Node<type> header;
	private Exception noSuchElement;

	public MyLLIteratorS(Node<type> current)
	{
		this.current = current;
		this.header = current;
		noSuchElement = new Exception("NoSuchElementException");
	}

	/**
	 * 
	 * @return true if a previouse node exists on the list
	 */
	public boolean hasPrev()
	{
		return current != header;
	}

	/**
	 * resets the current place of the iterator to initial place(first element
	 * on the list)
	 */
	public void restart()
	{
		current = header;
	}

	/**
	 * 
	 * @return the previous element in the list
	 * @throws Exception
	 *             if called while there's no next node on the list
	 * 
	 */
	public type getPrev() throws Exception
	{
		if (!hasPrev())
			throw noSuchElement;
		Node<type> cur = header;
		while (cur.getNext() != current)
		{
			cur = cur.getNext();
		}
		type tmp = current.getElement(); // Check that
		current = cur;
		return tmp;
	}

	/**
	 * 
	 * @return if a next node exists on the list
	 */
	public boolean hasNext()
	{
		return current.getNext() != null;
	}

	/**
	 * 
	 * @return the next element on the list
	 * @throws Exception
	 *             if called while there's no next node on the list
	 */
	public type getNext() throws Exception
	{
		if (!hasNext())
			throw noSuchElement;
		current = current.getNext();
		return current.getElement();
	}
}
