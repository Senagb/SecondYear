package Parser;

public class DeleteParser extends Parser {

	public DeleteParser(String q) throws Exception {
		super(q);
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
	public String getTableName() {
		if (tokens[1].equals("from"))
			return tokens[2];
		return tokens[3];
	}

	@Override
	public String getQueryType() {
		return tokens[0];
	}

	@Override
	public boolean valid() {
		// delete from hazem where (name ='gedeed');
		return statement.matches(regex.delete);
	}

	@Override
	public String[] getColTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}