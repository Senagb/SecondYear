package Queue;
import SLinkedList.*;

public class MyQueue<type> extends MyLinkedListS<type>
{
	private static Exception queueEmpty = new Exception("QueueEmptyException");
	public void enqueue(type element)
	{
		add(element);
	}

	public type dequeue() throws Exception
	{

		if (isEmpty())
			throw queueEmpty;
		else
		{
			type temp = get(0);
			remove(0);
			return temp;
		}
	}
	}
