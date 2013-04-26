package Parser;

public class regex {
	static String param = "(\\(\\s*((('(\\w|\\s)+')|(\\w+))\\s*,\\s*)*(('(\\w|\\s)+')|(\\w+))\\s*\\))";

	static String condition1 = "(\\w+\\s*=\\s*\\'?\\s*(\\w|\\.)+\\s*\\'?)";
	static String condition2 = "(\\w+\\s*(=|>|<|(<=)|(>=))\\s*\\'?\\s*(\\w|\\.)+\\s*\\'?)";

	static String where = "((where\\s+\\(?(((" + condition1
			+ "\\s+and\\s)+)|((" + condition1 + "\\s+or\\s+)+))(" + condition1
			+ ")\\)?)|(where\\s+(\\(\\s+)?" + condition2 + "(\\s+\\))?))";

	static String insert = "insert\\s+into\\s+\\w+\\s+((" + param
			+ ")\\s+)?(values\\s+(" + param + ")\\s*)\\s*";

	static String create = "create table +([a-z]\\w+)\\s*(\\(\\s*(\\w+\\s+\\w+(\\(\\s*[0-9]+\\s*\\))?\\s*,\\s*)*\\w+\\s*\\w+(\\(\\s*[0-9]+\\s*\\))?\\s*\\))\\s*";

	static String delete = "delete\\s+((\\*|(\\w+))\\s+)?from\\s+\\w+\\s*"
			+ "(\\s+" + where + ")" + "?\\s*";

	static String select = "select\\s+(\\w+|(\\*))\\s+from\\s+\\w+(\\s+"
			+ where + ")?\\s*";

	static String drop = "drop table \\w+\\s*";

	static String updateParam = "(\\(?\\s*((\\w+=('(\\w|\\s)+')|(\\w+))\\s*,\\s*)*\\w+=(('(\\w|\\s)+')|(\\w+))\\s*\\)?)";
	static String update = "update\\s+\\w+\\s+set\\s*(" + updateParam + ")"
			+ "(\\s+" + where + ")?\\s*";

	public static void main(String[] args) {

		// // check
		//
		// // create
		// System.out
		// .println("create\n"
		// + "create table COFFEES (COF_NAME VARCHAR(20), SUP_ID Integer )"
		// .toLowerCase().matches(create));
		//
		// // delete
		// System.out.println("\ndelete\n"
		// + "delete from hazem where (name ='gedeed')".toLowerCase()
		// .matches(delete));
		//
		// // insert
		// System.out
		// .println("\ninsert\n"
		// +
		// "INSERT INTO Persons ('P_Id', LastName, FirstName) VALUES (5, 'Tjessem', 'Jakob')"
		// .toLowerCase().matches(insert));
		//
		// // select
		// System.out.println("\nselect\n"
		// + "select * from ahmed where (name > 50 )".toLowerCase()
		// .matches(select));
		//
		// // update set
		// System.out
		// .println("\nupdate set\n"
		// +
		// "UPDATE Persons SET (Address='Nissestien 67', City='Sandnes') WHERE (FirstName='Jakob' and FirstName='Jakob' and FirstName=Jakob)"
		// .toLowerCase().matches(update));
		//
		// // drop
		// System.out.println("\ndrop\n"
		// + "DROP TABLE table_name".toLowerCase().matches(drop));
		//
		System.out.println("DELETE  FROM country where SIZE >= 11.	5 ".toLowerCase().matches(delete));
	}

}
