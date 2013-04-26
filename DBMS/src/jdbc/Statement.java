package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import DBMS.DataBase;
import Logger.Loggy;

public class Statement implements java.sql.Statement {

	private DataBase data;
	private LinkedList<String> batchs;
	private final int SUCCESS_NO_INFO = 1;
	private final int EXECUTE_FAILED = -1;
	private int QueryTimeOut = 10;
	private Connection con;
	Logger logger = Loggy.getInstance();

	public Statement(DataBase db, Connection c) {
		batchs = new LinkedList<String>();
		data = db;
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		sql = sql.toLowerCase();
		if (!sql.startsWith("inserte") && !sql.startsWith("update"))
			throw new SQLException("Invalid Command To Add");
		batchs.add(sql);
	}

	@Override
	public void cancel() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearBatch() throws SQLException {
		batchs.clear();
	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws SQLException {
		data.Close();
		data = null;
		batchs = null;
	}

	@Override
	public boolean execute(String sql) throws SQLException {
		try {
			data.execute(sql);
			logger.info("Query has been excuted");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean execute(String arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, int[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String arg0, String[] arg1) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] executeBatch() throws SQLException {
		int k = 0;
		Iterator<String> itr = batchs.iterator();
		while (itr.hasNext()) {
			try {
				executeUpdate(itr.next());
				logger.info("Batch has been excuted");
				k++;
			} catch (Exception e) {
				break;
			}
		}
		int[] ret = new int[batchs.size()];
		for (int i = 0; i < ret.length; i++) {
			if (i < k)
				ret[i] = SUCCESS_NO_INFO;
			ret[i] = EXECUTE_FAILED;
		}
		return ret;
	}

	@SuppressWarnings("static-access")
	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		sql = sql.toLowerCase();
		if (!sql.startsWith("select"))
			throw new SQLException();
		try {
System.out.println();
			data.execute(sql);
			logger.info("Query has been excuted");
		} catch (Exception e) {
			throw new SQLException();
		}
		return new jdbc.Resultset(data.getTable(), this, data.getSchema());

	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		sql = sql.toLowerCase();
		if (sql.startsWith("select"))
			throw new SQLException();
		try {
			data.execute(sql);
			logger.info("Query has been excuted");
		} catch (Exception e) {
			throw new SQLException();
		}
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, int arg1) throws SQLException {
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		return 0;
	}

	@Override
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		return 0;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return con;
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return 0;
	}

	@Override
	public int getFetchSize() throws SQLException {
		return 0;
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		return null;
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		return 0;
	}

	@Override
	public int getMaxRows() throws SQLException {
		return 0;
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		return false;
	}

	@Override
	public boolean getMoreResults(int arg0) throws SQLException {
		return false;
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		return QueryTimeOut;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		return null;
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		return 0;
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		return 0;
	}

	@Override
	public int getResultSetType() throws SQLException {
		return 0;
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return 0;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	@Override
	public boolean isClosed() throws SQLException {
		return false;
	}

	@Override
	public boolean isPoolable() throws SQLException {
		return false;
	}

	@Override
	public void setCursorName(String arg0) throws SQLException {

	}

	@Override
	public void setEscapeProcessing(boolean arg0) throws SQLException {

	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {

	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {

	}

	@Override
	public void setMaxFieldSize(int arg0) throws SQLException {

	}

	@Override
	public void setMaxRows(int arg0) throws SQLException {

	}

	@Override
	public void setPoolable(boolean arg0) throws SQLException {

	}

	@Override
	public void setQueryTimeout(int q) throws SQLException {
		QueryTimeOut = q;
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
