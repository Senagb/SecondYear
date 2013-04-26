package DBMS;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Load {
	Table table;
	NodeList List;

	/**
	 * Constructor
	 */
	public Load() {
	}

	/**
	 * 
	 * @param tableName
	 * @param schema
	 * @throws Exception
	 */
	public void execute(String tableName, Schema schema) throws Exception  {

		/**
		 * reading xml
		 * */
		File file = new File(tableName + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		document.getDocumentElement().normalize();
		List = document.getElementsByTagName("Row");
		String[] columns = getFromSchema(tableName, schema);
		table = new Table(1, columns.length, tableName);
		
		/**
		 *  setting column names into first row in table
		 * */
		for (int i = 0; i < columns.length; i++)
			table.setValue(0, i, columns[i]);
		
		/**
		 * filling the table
		 * */
		int n = List.getLength();
		for (int i = 0; i < n; i++) {
			Node node = List.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				table.addRow();
				for (int j = 0; j < columns.length; j++) {
					try {
						table.setValue(i + 1, j,
								getTagValue(columns[j], eElement));
					} catch (Exception e) {
						table.setValue(i + 1, j, "");
					}
				}

			}
		}

	}

	/**
	 * 
	 * @param sTag
	 * @param eElement
	 * @return string
	 */
	protected static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

	/**
	 * 
	 * @param tableName
	 * @param schema
	 * @return string[]
	 * @throws FileNotFoundException
	 */
	private String[] getFromSchema(String tableName, Schema schema)
			throws FileNotFoundException {
		String[] names = schema.getColNames(tableName);
		return names;
	}

	/**
	 * 
	 * @return List
	 */
	public NodeList getList() {
		return List;
	}

	/**
	 * 
	 * @return table
	 */
	public Table getTable() {
		return table;
	}

}
