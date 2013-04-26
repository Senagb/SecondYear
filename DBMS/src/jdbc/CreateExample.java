package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateExample {

	public static void main(String args[]) {
		String url = "jdbc:CSEDBMS";
		java.sql.Connection con;
//		String createString;
//		createString = "create table COFFEES " + "(COF_NAME VARCHAR(25), "
//				+ "SUP_ID INTEGER" + ",COF_NAME VARCHAR(255)"
//				+ ",COF_NAME Float)";
		java.sql.Statement stmt;
		try {
			Class.forName("jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, "zankozy", "zankozty");
			stmt = con.createStatement();
			// stmt.execute("CREATE TABLE TEST2(P_Id integer" +
			// ",ID integer,age integer,City varchar(255))");
			stmt.execute("INSERT INTO TEST2 VALUES (40 , 50,15 ,'zankalony pop 3aleko')");
			// stmt.execute("SELECT * FROM Persons WHERE City='Sandnes'");
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}
