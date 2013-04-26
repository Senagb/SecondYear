package bufmgr;

import java.math.BigInteger;
import java.util.LinkedList;

/**
 * this hashtable is the one implemented to be used in the buffer manager
 * 
 * 
 * */
public class Hashtable {

	private LinkedList<holder>[] directory; // the table
	private int size;

	public Hashtable(int size) {
		// inistialize the hash table

		BigInteger k = new BigInteger("" + size);
		BigInteger prime = k.nextProbablePrime();
		directory = new LinkedList[prime.intValue()];

	}

	// function of hashing
	private int function(int key) {
		return (10 * key + 5) % directory.length;
	}

	// remove all the elements
	public void clear() {
		for (int i = 0; i < directory.length; i++) {
			directory[i] = null;
			size = 0;
		}

	}

	// check if the key exists in the hashtable
	public boolean containsKey(int key) {
		if (directory[function(key)] != null && find(function(key), key) != -1)
			return true;
		else
			return false;
	}

	// return the value of the key and -1 if doesn't exist
	public int get(int key) {
		if (directory[function(key)] != null)
			return find(function(key), key);
		else
			return -1;
	}

	// search for the value
	private int find(int function, int key) {
		for (holder l : directory[function]) {
			if (l.key == key)
				return l.pos;
		}
		return -1;
	}

	// if the hashtable is empty
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	// put new data in the hash table
	public void put(int key, int obj) {
		if (!containsKey(key)) {
			int x = function(key);
			if (directory[x] == null)
				directory[x] = new LinkedList<holder>();
			directory[x].add(new holder(key, obj));
			size++;
		}
	}

	// remove data from hashtable
	public int remove(int key) {
		int r = delete(key, function(key));
		size--;
		return r;
	}

	// delete the element 
	private int delete(int key, int function) {
		for (holder l : directory[function]) {
			if (l.key == key) {
				int index = directory[function].indexOf(l);
				int value = l.pos;
				directory[function].remove(index);
				return value;
			}

		}
		return -1;
	}

	public int size() {

		return size;
	}

}

class holder {
	int key;
	int pos;

	public holder(int k, int p) {
		// TODO Auto-generated constructor stub
		key = k;
		pos = p;
	}
}
