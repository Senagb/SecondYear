package DBMS;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.log4j.Logger;

import Logger.Loggy;

public class Insert {
	
	Logger logger = Loggy.getInstance();
	
	public Insert() {
	
		logger = Loggy.getInstance();
	}

	public void excute(Table table, String[] columns, String[] values) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		if (columns == null) {
			// throw exception if the length isn't equal
			table.addRow();
			int index = table.getLength() - 1;
			for (int i = 0; i < values.length; i++) {
				table.setValue(index, i, values[i]);
			}
		} else {
			table.addRow();
			int index = table.getLength() - 1;
			for (int i = 0; i < columns.length; i++) {
				int colNumber = table.getColumnIndex(columns[i]);

				table.setValue(index, colNumber, values[i]);
			}
		}
		
		logger.info("A row inserted at Table " + table.TableName);
	}
}
