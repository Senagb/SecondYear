package DBMS;

import java.io.FileReader;
import java.io.FileWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Schema")
@XmlAccessorType(XmlAccessType.FIELD)
public class Schema {

	@XmlElement
	private LinkedList<tableData> tables = new LinkedList<tableData>();
	private static Schema ref;

	private Schema() {

	}

	public static Schema getinstance() {
		if (ref == null) {
			ref = new Schema();
		}
		return ref;
	}

	// to add new table to the schema file abd return false if table with
	// the
	// same name exists
	public boolean add(tableData table) throws Exception {
		if (!contain(table.getTableName())) {
			tables.add(table);
			return true;
		} else {
			throw new Exception("table with the same name exists");
		}
	}

	// to drop table from the schema file return false if the table donot exists
	public boolean drop(String name) throws Exception {
		if (tables.size() != 0 && contain(name)) {
			tables.remove(indexGtter(name));
			return true;
		} else {
			throw new Exception(
					"the dataBase is empty OR this table donot exists");
		}
	}

	// return null in case the data is mismatched
	public boolean check(tableData table) throws Exception {
		if (tables.size() != 0 && contain(table.getTableName())) {
			tableData t = tables.get(indexGtter(table.getTableName()));
			return tableData
					.equals(t, table.getTableName(), table.getColumns());
		}
		throw new Exception("check input data types");
	}

	// save the schema file
	public void save(Schema t) throws Exception {
		final JAXBContext jaxbContext = JAXBContext.newInstance(Schema.class);
		FileWriter w = new FileWriter("Schema.xml");
		jaxbContext.createMarshaller().marshal(t, w);
	}

	// load the schema file
	public void load() throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(Schema.class);
		Schema t = (Schema) jaxbContext.createUnmarshaller().unmarshal(
				new FileReader("Schema.xml"));
		this.tables = t.tables;
	}

	// get the columns of certain table
	public String[][] getColumns(String name) {
		tableData t = null;
		for (tableData r : tables) {
			if (r.getTableName().equals(name)) {
				t = r;
				break;
			}
		}
		return t.getColumns();
	}

	// get the index of certain tbale in the ll
	private int indexGtter(String name) {
		for (tableData t1 : tables) {
			if (t1.getTableName().equalsIgnoreCase(name))
				return tables.indexOf(t1);
		}
		return 0;
	}

	// check if the table existis
	private boolean contain(String name) {
		for (tableData t : tables) {
			if (t.getTableName().equals(name))
				return true;
		}
		return false;
	}

	// get columns name of the tablee
	public String[] getColNames(String name) {

		String[][] c = getColumns(name);
		String[] col = new String[c.length];
		for (int i = 0; i < c.length; i++) {
			col[i] = c[i][0];
		}
		return col;
	}

	public boolean checkNames(String name, String[] v) {
		boolean check = true;
		String[] c = getColNames(name);
		HashSet<String> h = new HashSet<String>();
		for (int i = 0; i < c.length; i++) {
			h.add(c[i]);
		}
		for (int i = 0; i < v.length; i++) {
			if (!h.contains(v[i])) {
				check = false;
				break;
			}
		}
		return check;
	}

	// extract input types
	public String[] checkTypes(String[] in) {
		// output array of the columns and their corresponding type
		// System.out.println(in);
		String[] out = new String[in.length];

		String string = "varchar";
		String date = "date";

		for (int i = 0; i < out.length; i++) {
			String temp = in[i];
			if (temp.contains("\'")) {
				out[i] = string;
				out[i] += "_" + (in[i].length()-2);
			} else if (temp.contains("/")) {
				out[i] = date;
			} else {
				String type = numchecker(temp);
				out[i] = type;
			}

		}
		return out;
	}

	private String numchecker(String temp) {
		try {
			Integer.parseInt(temp);
			return "int";
		} catch (Exception e) {
			try {
				Long.parseLong(temp);
				return "long";
			} catch (Exception z) {
				try {
					Float.parseFloat(temp);
					return "float";
				} catch (Exception g) {
					try {
						Double.parseDouble(temp);
						return "double";
					} catch (Exception h) {
						// TODO: handle exception
					}
				}

			}

		}
		return null;
	}

	public LinkedList<tableData> getTables() {
		return tables;
	}

	public String[] getTableNames() {
		String[] names = new String[tables.size()];
		for (int i = 0; i < tables.size(); i++)
			names[i] = tables.get(i).getTableName();
		return names;
	}

	public HashMap<String, String> getTableData(String name) {
		HashMap<String, String> s = new HashMap<String, String>();
		String[][] col = getColumns(name);
		for (int i = 0; i < col.length; i++) {
			s.put(col[i][0], col[i][1]);
		}
		return s;
	}
}
