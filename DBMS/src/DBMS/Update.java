package DBMS;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.log4j.*;
import Logger.Loggy;

public class Update {

	Logger logger;

	public Update() {

		logger = Loggy.getInstance();
	}

	public void execute(Table table, String[] columns, String[] values,
			int[] rowNumber) throws ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		if (rowNumber == null)
			for (int i = 0; i < columns.length; i++)
				for (int j = 1; j < table.getLength(); j++)
					table.setValue(j, table.getColumnIndex(columns[i]),
							values[i]);
		else {
			for (int i = 0; i < columns.length; i++)
				for (int j = 0; j < rowNumber.length; j++)
					table.setValue(rowNumber[j],
							table.getColumnIndex(columns[i]), values[i]);
		}

		logger.info("Table " + table.TableName + " has been updated");
	}
}
