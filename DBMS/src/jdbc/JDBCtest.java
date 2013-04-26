package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import junit.framework.TestCase;

public class JDBCtest extends TestCase {

	String[] Create, Drop, Delete, Insert, Update, Select;

	String[] tableName = { "MARKETS", "PERSON", "country" };

	String[][] columsName = { { "MARK_NAME", "MARK_ID", "MARK_PROFITES" },
			{ "PERSON_NAME", "PERSON_ID", "PERSON_ADREES" },
			{ "NAME", "POPULATION", "SIZE" } };

	String[] Drop() {

		String[] Drop = new String[3];
		Drop[0] = "DROP TABLE " + tableName[0];
		Drop[1] = "DROP TABLE " + tableName[1];
		Drop[2] = "DROP TABLE " + tableName[2];

		return Drop;
	}

	String[] Update() {

		String[] update = new String[4];

		update[0] = "UPDATE " + tableName[0] + " SET " + columsName[0][0]
				+ " = 'carfoor' , " + columsName[0][1] + " = 600 WHERE "
				+ columsName[0][0] + "= 'plaza' ";

		update[1] = "UPDATE " + tableName[0] + " SET " + columsName[0][0]
				+ " = 'Fathala' , " + columsName[0][1] + " = 600  WHERE "
				+ columsName[0][0] + " = 'citystar'  or  " + columsName[0][1]
				+ " = 15";

		update[2] = "UPDATE " + tableName[1] + " SET " + columsName[1][0]
				+ " = 'Nissestien 67', " + columsName[1][1] + "= 1000";

		update[3] = "UPDATE " + tableName[2] + " SET " + columsName[2][0]
				+ " ='lebenon', " + columsName[2][1] + " = 5000000 WHERE "
				+ columsName[2][1] + " > 1000";

		return update;
	}

	String[] createTable() {
		String[] create = new String[3];
		create[0] = "create table " + tableName[0] + " " + "("
				+ columsName[0][0] + " VARCHAR(25), " + columsName[0][1]
				+ " int" + "," + columsName[0][2] + " Double)";

		create[1] = "create table " + tableName[1] + " " + "("
				+ columsName[1][0] + " VARCHAR(25), " + columsName[1][1]
				+ " int" + "," + columsName[1][2] + " Double)";

		create[2] = "create table " + tableName[2] + " " + "("
				+ columsName[2][0] + " VARCHAR(25), " + columsName[2][1]
				+ " int" + "," + columsName[2][2] + " Double)";
		return create;
	}

	String[] deleteTable() {
		String[] delete = new String[4];

		delete[0] = "DELETE * FROM " + tableName[0] + " where "
				+ columsName[0][0] + " = 'plaza'  or " + columsName[0][0]
				+ " = 'Genash'";
		delete[1] = "DELETE * FROM " + tableName[1];
		delete[2] = "DELETE * FROM " + tableName[2];

		delete[3] = "DELETE  FROM " + tableName[2] + " where "
				+ columsName[2][2] + " > 11.5 ";

		return delete;
	}

	String[] insert() {
		String[] insert = new String[9];

		insert[0] = "INSERT INTO " + tableName[0]
				+ " VALUES ('Genash' , 15 , 12002.56)";

		insert[1] = "INSERT INTO " + tableName[0]
				+ " VALUES ('plaza' , 115 , 1542002.56)";

		insert[2] = "INSERT INTO " + tableName[0]
				+ " VALUES ('cityStar' , 200 , 12002.56)";

		insert[3] = "INSERT INTO " + tableName[1] + " (" + columsName[1][0]
				+ " ," + columsName[1][1] + " ) VALUES ('ZNKL',1515)";

		insert[4] = "INSERT INTO " + tableName[1] + " (" + columsName[1][1]
				+ " ," + columsName[1][0] + " ) VALUES (45 ,'HIMA')";

		insert[5] = "INSERT INTO " + tableName[1] + " (" + columsName[1][0]
				+ " ," + columsName[1][1] + " ) VALUES ('HANAFY',569)";

		insert[6] = "INSERT INTO " + tableName[2]
				+ " VALUES ('EGYPT' , 85 , 12002.56)";

		insert[7] = "INSERT INTO " + tableName[2]
				+ " VALUES ('Katr' , 11 , 2.56)";

		insert[8] = "INSERT INTO " + tableName[2]
				+ " VALUES ('morocco' , 20, 12002.56)";
		return insert;
	}

	String[] select() {

		String[] select = new String[12];
		select[0] = "SELECT * FROM " + tableName[0];
		select[1] = "SELECT * FROM " + tableName[1];
		select[2] = "SELECT * FROM " + tableName[2];

		select[3] = "SELECT " + columsName[0][0] + " FROM " + tableName[0];
		select[4] = "SELECT " + columsName[1][0] + " FROM " + tableName[1];
		select[5] = "SELECT " + columsName[2][0] + " FROM " + tableName[2];

		select[6] = "SELECT * FROM " + tableName[0] + " WHERE "
				+ columsName[0][1] + " > 50 ";

		select[7] = "SELECT * FROM " + tableName[1] + " WHERE "
				+ columsName[1][0] + " = 'HIMA' and " + columsName[1][1]
				+ " = 569";
		select[8] = "SELECT * FROM " + tableName[2] + " WHERE "
				+ columsName[2][2] + " > 1";

		select[9] = "SELECT " + columsName[1][0] + " FROM " + tableName[1]
				+ " WHERE " + columsName[1][2] + " =  1 or " + columsName[1][0]
				+ "= 'hanafy'";

		select[10] = "SELECT " + columsName[2][0] + " FROM " + tableName[2]
				+ " WHERE " + columsName[2][1] + " = 85 or " + columsName[2][1]
				+ " = 20 ";

		return select;

	}

	public void test() throws SQLException {

		String url = "jdbc:CSEDBMS";
		java.sql.Connection con;
		java.sql.Statement stmt;
		try {
			Class.forName("jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		con = DriverManager.getConnection(url, "csed2014", "csed2014");
		stmt = con.createStatement();

		try {

			/************** TESTING ****************/

			/************ Insert cases *******************/
			Create = createTable();
			Delete = deleteTable();
			Drop = Drop();
			Insert = insert();
			Update = Update();
			Select = select();

			/****************** run test ***************************/
			/*
			 * Create tables
			 */

			stmt.executeUpdate(Create[0]);// create table 1
			stmt.executeUpdate(Create[1]);// create table 2
			stmt.executeUpdate(Create[2]);// create table 3
			/*
			 * Insert
			 */

			for (int i = 0; i < 100; i++)	stmt.executeUpdate(Insert[i % 9]);

			
			/*****
			 * 
			 * 
			 * Test Table 1
			 * 
			 * 
			 * 
			 */

			// select 1 from in table 1
			java.sql.ResultSet rs = stmt.executeQuery(Select[0]);
			rs.next();
			assertEquals("'genash'", rs.getString(0));
			assertEquals(15, rs.getInt(1));
			assertEquals(12002.56, rs.getDouble(2));

			rs.next();
			assertEquals("'plaza'", rs.getString(0));
			assertEquals(115, rs.getInt(1));
			assertEquals(1542002.56, rs.getDouble(2));

			java.sql.ResultSet rs3 = stmt.executeQuery(Select[6]);
			rs3.next();
			assertEquals("'plaza'", rs3.getString(0));

			/************ TESt METADATA ***************/

			int[] retTypes = { java.sql.Types.VARCHAR, java.sql.Types.DATE,
					java.sql.Types.DOUBLE, java.sql.Types.INTEGER,
					java.sql.Types.INTEGER, java.sql.Types.FLOAT };

			java.sql.ResultSetMetaData mt = rs3.getMetaData();

			assertEquals(3, mt.getColumnCount());
			assertEquals(retTypes[0], mt.getColumnType(0));
			assertEquals(retTypes[4], mt.getColumnType(1));
			assertEquals(retTypes[2], mt.getColumnType(2));

			assertEquals("mark_name", mt.getColumnName(0));
			assertEquals("mark_id", mt.getColumnName(1));
			assertEquals("mark_profites", mt.getColumnName(2));
			assertEquals(tableName[0].toLowerCase(), mt.getTableName(0));

			/*************
			 * 
			 * 
			 * test table 2
			 */
			java.sql.ResultSet rs1 = stmt.executeQuery(Select[1]);
			rs1.next();

			assertEquals("'znkl'", rs1.getString(0));
			assertEquals(1515, rs1.getInt(1));
			rs1.next();

			assertEquals("'hima'", rs1.getString(0));
			assertEquals(45, rs1.getInt(1));

			/****
			 * 
			 * test table 3
			 * 
			 */

			java.sql.ResultSet rs4 = stmt.executeQuery(Select[10]);
			rs4.next();
			assertEquals("'egypt'", rs4.getString(0));
			rs4.next();
			assertEquals("'morocco'", rs4.getString(0));

			/*****
			 * 
			 * 
			 * Delet and then select
			 * 
			 * 
			 */

			stmt.executeUpdate(Delete[3]);
			java.sql.ResultSet dl = stmt.executeQuery(Select[8]);
			dl.next();
			assertEquals("'katr'", dl.getString(0));
			assertEquals(11, dl.getInt(1));

			stmt.executeUpdate(Delete[0]);

			java.sql.ResultSet dl2 = stmt.executeQuery(Select[3]);
			dl2.next();
			assertEquals("'citystar'", dl2.getString(0));

			/*******
			 * 
			 * 
			 * Update
			 * 
			 * 
			 */

			stmt.executeUpdate(Update[0]);
			stmt.executeUpdate(Update[1]);
			stmt.executeUpdate(Update[2]);
			stmt.executeUpdate(Update[3]);

			java.sql.ResultSet up = stmt.executeQuery(Select[3]);
			up.next();
			assertEquals("'fathala'", up.getString(0));

			java.sql.ResultSet up2 = stmt.executeQuery(Select[1]);
			up2.next();
			assertEquals("'nissestien 67'", up2.getString(0));

			/**
			 * Drop
			 */

			stmt.executeUpdate(Drop[0]);// create table 1
			stmt.executeUpdate(Drop[1]);// create table 2
			stmt.executeUpdate(Drop[2]);// create table 3
			/*******************************/
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		} finally {
			try {
				stmt.executeUpdate(Drop[0]);// create table 1
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				stmt.executeUpdate(Drop[1]);// create table 1
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				stmt.executeUpdate(Drop[2]);// create table 1
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}