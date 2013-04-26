package DBMS;

import org.apache.log4j.*;
import Logger.Loggy;

public class Select {

	Table required;
	Logger logger;
	public Select() {
		logger = Loggy.getInstance();
	
	}
	public void execute(Table table, String[] columns, int[] rowNumbers) {
		if (columns == null && rowNumbers == null) {
			// return all table;
			required = table;
		} else if (rowNumbers == null) {
			required = new Table(table.getLength(), columns.length,
					table.TableName);
			for (int i = 0; i < columns.length; i++) {
				int a = table.getColumnIndex(columns[i]);
				for (int j = 0; j < table.getLength(); j++)
					required.setValue(j, i, table.getValue(j, a));
			}
		}
		// from all table with condition
		else if (columns == null) {
			required = new Table(rowNumbers.length + 1,
					table.getColumnLength(), table.TableName);
			for (int i = 0; i < table.getColumnLength(); i++)
				required.setValue(0, i, table.getValue(0, i));

			for (int i = 0; i < rowNumbers.length; i++)
				for (int j = 0; j < table.getColumnLength(); j++)
					required.setValue(i + 1, j,
							table.getValue(rowNumbers[i], j));
		} else {
			required = new Table(rowNumbers.length + 1, columns.length,
					table.TableName);
			for (int i = 0; i < columns.length; i++)
				required.setValue(0, i, columns[i]);

			for (int i = 0; i < columns.length; i++) {
				int a = table.getColumnIndex(columns[i]);
				for (int j = 0; j < rowNumbers.length; j++)
					required.setValue(j + 1, i,
							table.getValue(rowNumbers[j], a));
			}
		}
		logger.info("Some data have been selected from Table "+ table.TableName);
	}
	public Table getRequired() {
		return required;
	}
}