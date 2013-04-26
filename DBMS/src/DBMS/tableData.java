package DBMS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TableData")
@XmlAccessorType(XmlAccessType.FIELD)
public class tableData {
	@XmlElement
	private String tableName;
	@XmlElement
	private String[][] columns;

	public tableData() {
	}

	public tableData(String name, String[][] c) {
		tableName = name;
		columns = c;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String[][] getColumns() {
		return columns;
	}

	public void setColumns(String[][] columns) {
		this.columns = columns;
	}

	// get the number of the row of the given column
	private int getrow(String cName) {
		for (int i = 0; i < columns.length; i++) {
			if (columns[i][0].equalsIgnoreCase(cName)) {
				return i;
			}
		}
		return -1;
	}

	// check the Data that the user sent and the data stored
	public static boolean equals(tableData t, String name,
			String[][] sentColumns) {
		// this array carries the each sent column name and type is write or
		// wrong
		boolean results = false;
		if (t.tableName.equalsIgnoreCase(name)) {

			for (int i = 0; i < sentColumns.length; i++) {

				int index = t.getrow(sentColumns[i][0]);// -1 means cannot find
														// that column
				if (index != -1) {
					if (sentColumns[i][1].charAt(0) == 'v') {
						String[] sent = sentColumns[i][1].split("_");
						String[] has = t.columns[i][1].split("_");
						if (sent[0].equals(has[0])
								&& Integer.parseInt(sent[1]) <= Integer
										.parseInt(has[1])) {
							results = true;
						}else {break;}
					} else if (t.columns[index][1].equals("double")) {
						if (sentColumns[i][1].equals("float")
								|| sentColumns[i][1].equals("double")
								|| sentColumns[i][1].equals("int")
								|| sentColumns[i][1].equals("long"))
							results = true;
						else 
							break;
					} else if (t.columns[index][1].equals("long")) {
						if (sentColumns[i][1].equals("int")
								|| sentColumns.equals("long"))
							results = true;
						else break;
					} else if (t.columns[index][1].equals(sentColumns[i][1])) {
						results = true;
					} else {
						results = false;
						break;
					}
				} else {
					results = false;
					break;
				}
			}
		}
		return results;
	}

}
