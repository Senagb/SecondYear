package DBMS;

import java.util.ArrayList;

public class Table {
	ArrayList<Data> list;
	int row, column; // the number of rows and columns in the table
	public String TableName;

	/**
	 * The Default Constructor initialize a Table with 2 rows and 1 colomn and
	 * name is the name of the table
	 */
	public Table(String name) {
		TableName = name;
		row = 2;
		column = 1;
		list = new ArrayList<Data>(row);
		for (int i = 0; i < row; i++)
			list.add(new Data(column));
	}

	public void clear() {
		list.clear();
	}

	public String getTableName() {
		return TableName;
	}

	/**
	 * Table Constructor initialize a Table with n rows and m colomns and name
	 * is the name of the table
	 */
	public Table(int n, int m, String name) {
		TableName = name;
		row = n;
		column = m;
		list = new ArrayList<Data>(row);
		for (int i = 0; i < row; i++)
			list.add(new Data(column));
	}

	/** Return the names of the columns */
	public String[] getColumnNames() {
		String[] names = new String[column];
		for (int i = 0; i < column; i++)
			names[i] = list.get(0).getValue(i);
		return names;
	}

	/** Get the Values of the table with out column name */
	public String[][] getValues() {
		String[][] values = new String[row - 1][column];
		for (int i = 1; i < row; i++)
			for (int j = 0; j < column; j++)
				values[i - 1][j] = list.get(i).getValue(j);
		return values;
	}

	public int getLength() {
		return row;
	}

	/** add A new row at the end of the Table */
	public void addRow() {
		row++;
		list.add(new Data(column));
	}

	/** add A new row at index */
	public void addRowAt(int index) {
		row++;
		list.add(index, new Data(column));
	}

	/** add A new Column at the end of the row in all Rows */
	public void addColumn() {
		column++;
		for (int i = 0; i < row; i++)
			list.get(i).addColumn();
	}

	/** add A new Coloumn at index in all Rows */
	public void addColumnAt(int index) {
		column++;
		for (int i = 0; i < row; i++)
			list.get(i).addColumnAt(index);
	}

	/** set Value at Row r and Column C with String s */
	public void setValue(int r, int c, String s) {
		list.get(r).setValue(c, s);
	}

	/** get Value at Row r and Column c */
	public String getValue(int r, int c) {
		return list.get(r).getValue(c);
	}

	public String getValue(int r, String s) {
		return list.get(r).getValue(getColumnIndex(s));
	}

	/***/
	public Data getRow(int i) {
		return list.get(i);
	}

	public int getColumnIndex(String s) {
		return list.get(0).getColumnIndex(s);
	}

	public void delete(int i) {
		row--;
		list.remove(i);
	}

	public String toString() {
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < row; i++)
			s.append(list.get(i).toString() + '\n');
		return s.toString();
	}

	public int getColumnLength() {
		return column;
	}
}
