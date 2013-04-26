package DBMS;

import java.util.ArrayList;

public class Data {
	ArrayList<String> column;

	public Data(int n) {

		column = new ArrayList<String>(n);
		for (int i = 0; i < n; i++)
			column.add(new String(""));
	}

	/** add A new Column at the end of the row */
	public void addColumn() {
		column.add(new String(""));
	}

	/** add A new Coloumn at index */
	public void addColumnAt(int index) {
		column.add(index, new String(""));
	}

	/** set value at index with String s */
	public void setValue(int index, String s) {
		column.set(index, s);
	}

	public String getValue(int index) {
		return column.get(index);
	}

	public int getColumnIndex(String s) {
		return column.indexOf(s);
	}

	public String toString() {
		return column.toString();
	}
}
