package DBMS;

import org.apache.log4j.*; 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import Logger.Loggy;

public class Delete {

	Logger logger;

	public Delete() {
		
		logger = Loggy.getInstance();
	}

	/**
	 * 
	 * @param table
	 * @param rows
	 * @throws ParserConfigurationException
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public void excute(Table table, int[] rows)
			throws ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {

		if (rows != null)
			for (int i = 0; i < rows.length; i++) {
				table.delete(rows[i] - i);
			}
		else {
			while (table.getLength() > 1) {
				table.delete(1);
			}
		}
	
		logger.info("Some data has been deleted from Table " + table.TableName);

	}
}
