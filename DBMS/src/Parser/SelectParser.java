package Parser;

public class SelectParser extends Parser {

	public SelectParser(String q) throws Exception {
		super(q);
	}

	@Override
	public boolean valid() {
		// select name from table
		return statement.matches(regex.select);
	}

	private int k = 1;

	@Override
	public String[] requiredColName() {
		if (tokens[1].equals("*"))
			return null;
		for (; k < tokens.length; k++)
			if (tokens[k].equals("from"))
				break;

		String[] res = new String[k - 1];
		int r = 0;
		for (int i = 1; i < k; i++)
			res[r++] = tokens[i];
		return res;
	}

	@Override
	public String[] requiredColValue() {
		return null;
	}

	@Override
	public String getTableName() {
		int i = 0;
		for (; k < tokens.length; k++)
			if (tokens[k].equals("from")) {
				i = k;
				break;
			}
		return tokens[i + 1];
	}

	@Override
	public String[] getColTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
