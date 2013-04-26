package Parser;

public class DropParser extends Parser {

	public DropParser(String q) throws Exception {
		super(q);
	}

	@Override
	public boolean valid() {
		return statement.matches(regex.drop);
	}

	@Override
	public String[] requiredColName() {
		return null;
	}

	@Override
	public String[] requiredColValue() {
		return null;
	}

	@Override
	public String[] conditionColName() {
		return null;
	}

	@Override
	public String[] conditionColValue() {
		return null;
	}

	@Override
	public String getTableName() {
		return tokens[2];
	}

	@Override
	public String getQueryType() {
		return tokens[0];
	}

	@Override
	public String getCondition() {
		return null;
	}

	@Override
	public String[] getColTypes() {
		return null;
	}

}
