package jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

import DBMS.Schema;
import DBMS.Table;

public class Resultset implements ResultSet {

	private Table table;
	private Schema schema;
	private jdbc.Statement stmt;
	private int cursor;
	private int numberofRows;

	public Resultset(Table t, jdbc.Statement st, Schema s) {
		table = t;
		stmt = st;
		numberofRows = t.getLength();
		cursor = 0;
		schema = s;
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {

		return null;
	}

	@Override
	public boolean absolute(int row) throws SQLException {
		if (row > 0 && row <= numberofRows) {
			cursor = row;
			return true;
		} else if (row < 0 && -row <= -numberofRows) {
			cursor = numberofRows + row;
			return true;
		} else
			return false;
	}

	@Override
	public void afterLast() throws SQLException {
		cursor = numberofRows + 1;
	}

	@Override
	public void beforeFirst() throws SQLException {
		cursor = 0;
	}

	@Override
	public void cancelRowUpdates() throws SQLException {

	}

	@Override
	public void clearWarnings() throws SQLException {

	}

	@Override
	public void close() throws SQLException {
		table.clear();
		table = null;
	}

	@Override
	public void deleteRow() throws SQLException {

	}

	@Override
	public int findColumn(String columLabel) throws SQLException {
		return table.getColumnIndex(columLabel);
	}

	@Override
	public boolean first() throws SQLException {
		if (numberofRows > 0) {
			cursor = 1;
			return true;
		}
		return false;
	}

	@Override
	public Array getArray(int arg0) throws SQLException {

		return null;
	}

	@Override
	public Array getArray(String arg0) throws SQLException {

		return null;
	}

	@Override
	public InputStream getAsciiStream(int arg0) throws SQLException {

		return null;
	}

	@Override
	public InputStream getAsciiStream(String arg0) throws SQLException {

		return null;
	}

	@Override
	public BigDecimal getBigDecimal(int arg0) throws SQLException {

		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String arg0) throws SQLException {

		return null;
	}

	@Override
	public BigDecimal getBigDecimal(int arg0, int arg1) throws SQLException {

		return null;
	}

	@Override
	public BigDecimal getBigDecimal(String arg0, int arg1) throws SQLException {

		return null;
	}

	@Override
	public InputStream getBinaryStream(int arg0) throws SQLException {

		return null;
	}

	@Override
	public InputStream getBinaryStream(String arg0) throws SQLException {

		return null;
	}

	@Override
	public Blob getBlob(int arg0) throws SQLException {

		return null;
	}

	@Override
	public Blob getBlob(String arg0) throws SQLException {

		return null;
	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		String s = table.getValue(cursor, columnIndex - 1);
		if (s.equals("true") || s.equals("1"))
			return true;
		return false;
	}

	@Override
	public boolean getBoolean(String columnLabel) throws SQLException {
		String s = table.getValue(cursor, columnLabel);
		if (s.equals("true") || s.equals("1"))
			return true;
		return false;
	}

	@Override
	public byte getByte(int arg0) throws SQLException {

		return 0;
	}

	@Override
	public byte getByte(String arg0) throws SQLException {

		return 0;
	}

	@Override
	public byte[] getBytes(int arg0) throws SQLException {

		return null;
	}

	@Override
	public byte[] getBytes(String arg0) throws SQLException {

		return null;
	}

	@Override
	public Reader getCharacterStream(int arg0) throws SQLException {

		return null;
	}

	@Override
	public Reader getCharacterStream(String arg0) throws SQLException {

		return null;
	}

	@Override
	public Clob getClob(int arg0) throws SQLException {

		return null;
	}

	@Override
	public Clob getClob(String arg0) throws SQLException {

		return null;
	}

	@Override
	public int getConcurrency() throws SQLException {

		return 0;
	}

	@Override
	public String getCursorName() throws SQLException {

		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Date getDate(int col) throws SQLException {
		String s = table.getValue(cursor, col);
		String[] DateString = s.split("/");
		return new Date(Integer.parseInt(DateString[0]),
				Integer.parseInt(DateString[1]),
				Integer.parseInt(DateString[2]));
	}

	@SuppressWarnings("deprecation")
	@Override
	public Date getDate(String Col) throws SQLException {
		String s = table.getValue(cursor, Col);
		String[] DateString = s.split("/");
		return new Date(Integer.parseInt(DateString[0]),
				Integer.parseInt(DateString[1]),
				Integer.parseInt(DateString[2]));
	}

	@Override
	public Date getDate(int arg0, Calendar arg1) throws SQLException {

		return null;
	}

	@Override
	public Date getDate(String arg0, Calendar arg1) throws SQLException {
		return null;
	}

	@Override
	public double getDouble(int col) throws SQLException {
		try {
			return Double.parseDouble(table.getValue(cursor, col));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public double getDouble(String colLab) throws SQLException {
		try {
			return Double.parseDouble(table.getValue(cursor, colLab));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return FETCH_FORWARD;
	}

	@Override
	public int getFetchSize() throws SQLException {

		return 0;
	}

	@Override
	public float getFloat(int col) throws SQLException {
		try {
			return Float.parseFloat(table.getValue(cursor, col));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public float getFloat(String colaLbel) throws SQLException {
		try {
			return Float.parseFloat(table.getValue(cursor, colaLbel));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public int getHoldability() throws SQLException {

		return 0;
	}

	@Override
	public int getInt(int col) throws SQLException {
		try {
			return Integer.parseInt(table.getValue(cursor, col));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public int getInt(String colLabel) throws SQLException {
		try {
			return Integer.parseInt(table.getValue(cursor, colLabel));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public long getLong(int col) throws SQLException {
		try {
			return Long.parseLong(table.getValue(cursor, col));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public long getLong(String colLabel) throws SQLException {
		try {
			return Long.parseLong(table.getValue(cursor, colLabel));
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		return new ResultSetMetaData(schema, table);
	}

	@Override
	public Reader getNCharacterStream(int arg0) throws SQLException {

		return null;
	}

	@Override
	public Reader getNCharacterStream(String arg0) throws SQLException {

		return null;
	}

	@Override
	public NClob getNClob(int arg0) throws SQLException {

		return null;
	}

	@Override
	public NClob getNClob(String arg0) throws SQLException {

		return null;
	}

	@Override
	public String getNString(int arg0) throws SQLException {

		return null;
	}

	@Override
	public String getNString(String arg0) throws SQLException {

		return null;
	}

	@Override
	public Object getObject(int Col) throws SQLException {
		return (Object) table.getValue(cursor, Col);
	}

	@Override
	public Object getObject(String arg0) throws SQLException {

		return null;
	}

	@Override
	public Object getObject(int arg0, Map<String, Class<?>> arg1)
			throws SQLException {

		return null;
	}

	@Override
	public Object getObject(String arg0, Map<String, Class<?>> arg1)
			throws SQLException {

		return null;
	}

	@Override
	public Ref getRef(int arg0) throws SQLException {

		return null;
	}

	@Override
	public Ref getRef(String arg0) throws SQLException {

		return null;
	}

	@Override
	public int getRow() throws SQLException {
		return cursor;
	}

	@Override
	public RowId getRowId(int arg0) throws SQLException {

		return null;
	}

	@Override
	public RowId getRowId(String arg0) throws SQLException {

		return null;
	}

	@Override
	public SQLXML getSQLXML(int arg0) throws SQLException {

		return null;
	}

	@Override
	public SQLXML getSQLXML(String arg0) throws SQLException {

		return null;
	}

	@Override
	public short getShort(int arg0) throws SQLException {

		return 0;
	}

	@Override
	public short getShort(String arg0) throws SQLException {

		return 0;
	}

	@Override
	public Statement getStatement() throws SQLException {
		return stmt;
	}

	@Override
	public String getString(int col) throws SQLException {
		return table.getValue(cursor, col);
	}

	@Override
	public String getString(String colLabel) throws SQLException {
		return table.getValue(cursor, colLabel);
	}

	@Override
	public Time getTime(int arg0) throws SQLException {

		return null;
	}

	@Override
	public Time getTime(String arg0) throws SQLException {

		return null;
	}

	@Override
	public Time getTime(int arg0, Calendar arg1) throws SQLException {

		return null;
	}

	@Override
	public Time getTime(String arg0, Calendar arg1) throws SQLException {

		return null;
	}

	@Override
	public Timestamp getTimestamp(int arg0) throws SQLException {

		return null;
	}

	@Override
	public Timestamp getTimestamp(String arg0) throws SQLException {

		return null;
	}

	@Override
	public Timestamp getTimestamp(int arg0, Calendar arg1) throws SQLException {

		return null;
	}

	@Override
	public Timestamp getTimestamp(String arg0, Calendar arg1)
			throws SQLException {

		return null;
	}

	@Override
	public int getType() throws SQLException {

		return 0;
	}

	@Override
	public URL getURL(int arg0) throws SQLException {

		return null;
	}

	@Override
	public URL getURL(String arg0) throws SQLException {

		return null;
	}

	@Override
	public InputStream getUnicodeStream(int arg0) throws SQLException {

		return null;
	}

	@Override
	public InputStream getUnicodeStream(String arg0) throws SQLException {

		return null;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {

		return null;
	}

	@Override
	public void insertRow() throws SQLException {

	}

	@Override
	public boolean isAfterLast() throws SQLException {
		return cursor > numberofRows;
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		return cursor == 0;
	}

	@Override
	public boolean isClosed() throws SQLException {
		if (table == null)
			return true;
		return false;
	}

	@Override
	public boolean isFirst() throws SQLException {
		return cursor == 1;
	}

	@Override
	public boolean isLast() throws SQLException {
		return cursor == numberofRows;
	}

	@Override
	public boolean last() throws SQLException {

		if (numberofRows > 0) {
			cursor = numberofRows;
			return true;
		}
		return false;
	}

	@Override
	public void moveToCurrentRow() throws SQLException {

	}

	@Override
	public void moveToInsertRow() throws SQLException {

	}

	@Override
	public boolean next() throws SQLException {
		if (cursor <= numberofRows) {
			cursor++;
			return true;
		}
		return false;
	}

	@Override
	public boolean previous() throws SQLException {
		if (cursor > 1) {
			cursor--;
			return true;
		}
		return false;
	}

	@Override
	public void refreshRow() throws SQLException {

	}

	@Override
	public boolean relative(int arg0) throws SQLException {

		return false;
	}

	@Override
	public boolean rowDeleted() throws SQLException {

		return false;
	}

	@Override
	public boolean rowInserted() throws SQLException {

		return false;
	}

	@Override
	public boolean rowUpdated() throws SQLException {

		return false;
	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {

	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {

	}

	@Override
	public void updateArray(int arg0, Array arg1) throws SQLException {

	}

	@Override
	public void updateArray(String arg0, Array arg1) throws SQLException {

	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1)
			throws SQLException {

	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1)
			throws SQLException {

	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, int arg2)
			throws SQLException {

	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, int arg2)
			throws SQLException {

	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateBigDecimal(int arg0, BigDecimal arg1) throws SQLException {

	}

	@Override
	public void updateBigDecimal(String arg0, BigDecimal arg1)
			throws SQLException {

	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1)
			throws SQLException {

	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1)
			throws SQLException {

	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, int arg2)
			throws SQLException {

	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, int arg2)
			throws SQLException {

	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateBlob(int arg0, Blob arg1) throws SQLException {

	}

	@Override
	public void updateBlob(String arg0, Blob arg1) throws SQLException {

	}

	@Override
	public void updateBlob(int arg0, InputStream arg1) throws SQLException {

	}

	@Override
	public void updateBlob(String arg0, InputStream arg1) throws SQLException {

	}

	@Override
	public void updateBlob(int arg0, InputStream arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateBlob(String arg0, InputStream arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateBoolean(int arg0, boolean arg1) throws SQLException {

	}

	@Override
	public void updateBoolean(String arg0, boolean arg1) throws SQLException {

	}

	@Override
	public void updateByte(int arg0, byte arg1) throws SQLException {

	}

	@Override
	public void updateByte(String arg0, byte arg1) throws SQLException {

	}

	@Override
	public void updateBytes(int arg0, byte[] arg1) throws SQLException {

	}

	@Override
	public void updateBytes(String arg0, byte[] arg1) throws SQLException {

	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1)
			throws SQLException {

	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1)
			throws SQLException {

	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, int arg2)
			throws SQLException {

	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, int arg2)
			throws SQLException {

	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateClob(int arg0, Clob arg1) throws SQLException {

	}

	@Override
	public void updateClob(String arg0, Clob arg1) throws SQLException {

	}

	@Override
	public void updateClob(int arg0, Reader arg1) throws SQLException {

	}

	@Override
	public void updateClob(String arg0, Reader arg1) throws SQLException {

	}

	@Override
	public void updateClob(int arg0, Reader arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateClob(String arg0, Reader arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateDate(int arg0, Date arg1) throws SQLException {

	}

	@Override
	public void updateDate(String arg0, Date arg1) throws SQLException {

	}

	@Override
	public void updateDouble(int arg0, double arg1) throws SQLException {

	}

	@Override
	public void updateDouble(String arg0, double arg1) throws SQLException {

	}

	@Override
	public void updateFloat(int arg0, float arg1) throws SQLException {

	}

	@Override
	public void updateFloat(String arg0, float arg1) throws SQLException {

	}

	@Override
	public void updateInt(int arg0, int arg1) throws SQLException {

	}

	@Override
	public void updateInt(String arg0, int arg1) throws SQLException {

	}

	@Override
	public void updateLong(int arg0, long arg1) throws SQLException {

	}

	@Override
	public void updateLong(String arg0, long arg1) throws SQLException {

	}

	@Override
	public void updateNCharacterStream(int arg0, Reader arg1)
			throws SQLException {

	}

	@Override
	public void updateNCharacterStream(String arg0, Reader arg1)
			throws SQLException {

	}

	@Override
	public void updateNCharacterStream(int arg0, Reader arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateNCharacterStream(String arg0, Reader arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateNClob(int arg0, NClob arg1) throws SQLException {

	}

	@Override
	public void updateNClob(String arg0, NClob arg1) throws SQLException {

	}

	@Override
	public void updateNClob(int arg0, Reader arg1) throws SQLException {

	}

	@Override
	public void updateNClob(String arg0, Reader arg1) throws SQLException {

	}

	@Override
	public void updateNClob(int arg0, Reader arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateNClob(String arg0, Reader arg1, long arg2)
			throws SQLException {

	}

	@Override
	public void updateNString(int arg0, String arg1) throws SQLException {

	}

	@Override
	public void updateNString(String arg0, String arg1) throws SQLException {

	}

	@Override
	public void updateNull(int arg0) throws SQLException {

	}

	@Override
	public void updateNull(String arg0) throws SQLException {

	}

	@Override
	public void updateObject(int arg0, Object arg1) throws SQLException {

	}

	@Override
	public void updateObject(String arg0, Object arg1) throws SQLException {

	}

	@Override
	public void updateObject(int arg0, Object arg1, int arg2)
			throws SQLException {

	}

	@Override
	public void updateObject(String arg0, Object arg1, int arg2)
			throws SQLException {

	}

	@Override
	public void updateRef(int arg0, Ref arg1) throws SQLException {

	}

	@Override
	public void updateRef(String arg0, Ref arg1) throws SQLException {

	}

	@Override
	public void updateRow() throws SQLException {

	}

	@Override
	public void updateRowId(int arg0, RowId arg1) throws SQLException {

	}

	@Override
	public void updateRowId(String arg0, RowId arg1) throws SQLException {

	}

	@Override
	public void updateSQLXML(int arg0, SQLXML arg1) throws SQLException {

	}

	@Override
	public void updateSQLXML(String arg0, SQLXML arg1) throws SQLException {

	}

	@Override
	public void updateShort(int arg0, short arg1) throws SQLException {

	}

	@Override
	public void updateShort(String arg0, short arg1) throws SQLException {

	}

	@Override
	public void updateString(int arg0, String arg1) throws SQLException {

	}

	@Override
	public void updateString(String arg0, String arg1) throws SQLException {

	}

	@Override
	public void updateTime(int arg0, Time arg1) throws SQLException {

	}

	@Override
	public void updateTime(String arg0, Time arg1) throws SQLException {

	}

	@Override
	public void updateTimestamp(int arg0, Timestamp arg1) throws SQLException {

	}

	@Override
	public void updateTimestamp(String arg0, Timestamp arg1)
			throws SQLException {
	}

	@Override
	public boolean wasNull() throws SQLException {
		return false;
	}

	@Override
	public <T> T getObject(int arg0, Class<T> arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getObject(String arg0, Class<T> arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
