package jdbc;

import java.sql.SQLException;

import DBMS.Schema;
import DBMS.Table;

public class ResultSetMetaData implements java.sql.ResultSetMetaData {
	private Schema schema;
	private int colLength;
	private Table table;

	public ResultSetMetaData(Schema s, Table t) {
		schema = s;
		table = t;
		colLength = table.getColumnLength();
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCatalogName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnClassName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getColumnCount() throws SQLException {
		return colLength;
	}

	@Override
	public int getColumnDisplaySize(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getColumnLabel(int col) throws SQLException {
		try {
			return table.getValue(0, col);
		} catch (Exception e) {
			throw new SQLException();
		}

	}

	@Override
	public String getColumnName(int col) throws SQLException {
		try {
			return table.getValue(0, col);
		} catch (Exception e) {
			throw new SQLException();
		}

	}

	@Override
	public int getColumnType(int col) throws SQLException {
		try {
			String type = (String) schema.getTableData(table.getTableName())
					.get(getColumnName(col));
			String[] s = { "varchar", "date", "double", "int", "long", "float" };
			int[] retTypes = { java.sql.Types.VARCHAR, java.sql.Types.DATE,
					java.sql.Types.DOUBLE, java.sql.Types.INTEGER,
					java.sql.Types.INTEGER, java.sql.Types.FLOAT };
			if(type.charAt(0) == 'v')
				return retTypes[0];
			for (int i = 0; i < s.length; i++)
				if (s[i].equals(type))
					return retTypes[i];
			return -1;
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public String getColumnTypeName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPrecision(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScale(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSchemaName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(int arg0) throws SQLException {
		return table.getTableName();
	}

	@Override
	public boolean isAutoIncrement(int arg0) throws SQLException {
		return true;
	}

	@Override
	public boolean isCaseSensitive(int arg0) throws SQLException {

		return false;
	}

	@Override
	public boolean isCurrency(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDefinitelyWritable(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int isNullable(int arg0) throws SQLException {
		return columnNullable;
	}

	@Override
	public boolean isReadOnly(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSearchable(int arg0) throws SQLException {
		return true;
	}

	@Override
	public boolean isSigned(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWritable(int a) throws SQLException {
		return true;
	}

}
