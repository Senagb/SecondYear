package Parser;

public class Controller {
	String statement;
	String validOperations = "set create table drop delete insert into select";
	Parser s;

	public Controller(String q) throws Exception {
		statement = q.toLowerCase();
		q = q.toLowerCase();
		if (q.startsWith("insert"))
			s = new InsertParser(statement);
		else if (q.startsWith("select"))
			s = new SelectParser(statement);
		else if (q.startsWith("create"))
			s = new CreateParser(statement);
		else if (q.startsWith("delete"))
			s = new DeleteParser(statement);
		else if (q.startsWith("update"))
			s = new UpdateParser(statement);
		else if (q.startsWith("drop"))
			s = new DropParser(statement);
		if (s == null)
			throw new Exception("syntax error");
	}

	public String[] requiredColName() {
		return s.requiredColName();
	}

	public String[] requiredColValue() {
		return s.requiredColValue();
	}

	public String[] conditionColName() {
		return s.conditionColName();
	}

	public String[] conditionColValue() {
		return s.conditionColValue();
	}

	public String getTableName() {
		return s.getTableName();
	}

	public String getQueryType() throws Exception {
		return s.getQueryType();
	}

	public String getCondition() {
		return s.getCondition();
	}

	public String[] getColTypes() {
		return s.getColTypes();
	}
}