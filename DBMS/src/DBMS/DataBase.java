package DBMS;

import java.util.HashSet;

import Parser.Controller;

public class DataBase {
	private static Table table;
	private String[] requiredColName;
	private String[] requiredColValues;
	private String[] conditionColName;
	private String[] conditionColValues;
	private String condition;
	private int[] rColumns;
	private String query;
	private String name;
	private Save save;
	private Controller parse;
	public static Schema schema;
	private static DataBase DataObj;
	private HashSet<String> standrds = new HashSet<String>();
	private String[] s = { "varchar", "date", "double", "int", "long", "float","boolean" };

	private DataBase() {
		for (int i = 0; i < s.length; i++) {
			standrds.add(s[i]);
		}
		schema = Schema.getinstance();
		try {
			schema.load();
		} catch (Exception e) {

		}
	}

	public static DataBase getInstance() throws Exception {
		if (DataObj == null)
			return DataObj = new DataBase();
		return DataObj;
	}

	public void Close() {
		DataObj = null;
	}

	public static Table getTable() {
		return table;
	}

	public void execute(String Query) throws Exception {
		parse = new Controller(Query);
		name = parse.getTableName();
		query = parse.getQueryType();
		requiredColName = parse.requiredColName();
		requiredColValues = parse.requiredColValue();
		conditionColName = parse.conditionColName();
		conditionColValues = parse.conditionColValue();
		condition = parse.getCondition();
		save = new Save();
		rColumns=null;
		/**
		 * loading the required file
		 * */
		if (!query.equalsIgnoreCase("create")) {
			Load load = new Load();
			load.execute(name, schema);
			table = load.getTable();
		}
		/**
		 * getting the rows needed to change checking the condition columns
		 * */
		if (conditionColName != null && conditionColValues != null) {
			if (!search1()) {
				throw new Exception("IncorrectColumnNamesOrTypes");
			}

		}
		/**
		 * checking the required columns
		 * */
		if (requiredColName != null && requiredColValues != null) {
			if (!search2()) {
				throw new Exception("IncorrectColumnNamesOrTypes");
			}
		}

		/**
		 * create new table
		 * */
		if (query.equalsIgnoreCase("Create")) {
			Create create = new Create(name, requiredColName);
			tableData data = new tableData(name, concatenate(
					parse.requiredColName(), parse.getColTypes()));
			if (!checktypes(parse.getColTypes())) {
				throw new Exception("IncorrectColumnNamesOrTypes");
			}
			schema.add(data);
			create.execute();
			schema.save(schema);
		} else {
			performOperation();
		}
	}

	/**
	 * search for the condition table check the condition column and values
	 * */
	private boolean search1() throws Exception {
		String[] cc = schema.checkTypes(conditionColValues);
		String[][] ccolumns = concatenate(conditionColName, cc);
		boolean cSchCheck = schema.check(new tableData(name, ccolumns));
		// throw exception
		Search search = new Search(table, conditionColName, conditionColValues,
				condition);
		if (conditionColName != null)
			rColumns = search.rowGetter();
		return cSchCheck;
	}

	/**
	 * check the required column names and values
	 * */
	private boolean search2() throws Exception {
		String[] cc = schema.checkTypes(requiredColValues);
		String[][] ccolumns = concatenate(requiredColName, cc);
		boolean cSchCheck = schema.check(new tableData(name, ccolumns));
		return cSchCheck;
	}

	// ------------------------------------------------------------
	// must be moved to another class as a factory method
	public void performOperation() throws Exception {
		/**
		 * drop a table
		 * */
		if (query.equalsIgnoreCase("Drop")) {
			Drop drop = new Drop();
			drop.execute(schema, name);
		}

		/**
		 * Insert into table
		 * */
		else if (query.equalsIgnoreCase("Insert")) {
			boolean schCheck = true;

			if (requiredColName == null) {

				String[] c = schema.checkTypes(requiredColValues);
				String[] columnsName = schema.getColNames(name);

				if (columnsName.length == c.length) {

					String[][] columns = concatenate(schema.getColNames(name),
							c);

					schCheck = schema.check(new tableData(name, columns));

				} else {

					schCheck = false;
				}
			}
			if (schCheck) {
				Insert insert = new Insert();
				insert.excute(table, requiredColName, requiredColValues);
				save.execute(table);
			} else {
				throw new Exception(
						"Incorrect column type");
			}
		}
		/**
		 * Select values from a table
		 * */
		else if (query.equalsIgnoreCase("Select")) {
			boolean check = true;
			if (requiredColName != null)
				check = schema.checkNames(name, requiredColName);
			// mistake in schema (select name from hanafy);
			if (check) {
				Select select = new Select();
				select.execute(table, requiredColName, rColumns);
				table = select.getRequired();

			} else {
				throw new Exception("Select Exception");
			}
		}
		/**
		 * Delete values from a table
		 * */
		else if (query.equalsIgnoreCase("Delete")) {
			Delete delete = new Delete();
			delete.excute(table, rColumns);
			save.execute(table);

		}
		/**
		 * Update a table
		 * */
		else if (query.equalsIgnoreCase("Update")) {

			Update update = new Update();

			update.execute(table, requiredColName, requiredColValues, rColumns);
			save.execute(table);
		}

	}

	// magdy
	private String[][] concatenate(String[] requiredColName2,
			String[] requiredColValue) {
		String[][] re = new String[requiredColName2.length][2];
		for (int i = 0; i < re.length; i++) {
			re[i][0] = requiredColName2[i];
			re[i][1] = requiredColValue[i];
		}
		return re;
	}

	public Schema getSchema() {
		return schema;
	}

	// magdy
	private boolean checktypes(String[] c) throws Exception {
		for (int i = 0; i < c.length; i++) {
			if (c[i].charAt(0) == 'v') {
				String[] s = c[i].split("_");
				if (!standrds.contains(s[0]))
					throw new Exception("the entered type is invalid");
			} else if (!standrds.contains(c[i].trim()))
				throw new Exception("the entered type is invalid");
		}
		return true;
	}

}
