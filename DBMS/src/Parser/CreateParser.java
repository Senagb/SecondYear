package Parser;

public class CreateParser extends Parser {
	public CreateParser(String q) throws Exception {
		super(q);
		getCol();
	}
	private String[] colName;
	private String[] colType;

	private void getCol() {
		String[] tmp1 = new String[tokens.length];
		String[] tmp2 = new String[tokens.length];
		int r = 0;
		for (int i = 3; i < tokens.length; i += 2) {
			tmp1[r] = tokens[i];
			if (tokens[i + 1].equals("varchar")) {
				tmp2[r++] = tokens[i + 1] + "_" + tokens[++i + 1];
			} else {
				tmp2[r++] = tokens[i + 1];
			}
		}
		colName = new String[r];
		colType = new String[r];
		for (int i = 0; i < r; i++) {
			colName[i] = tmp1[i];
			colType[i] = tmp2[i];
		}
	}

	@Override
	public String[] requiredColName() {
		return colName;
	}

	@Override
	public String[] getColTypes() {
		return colType;
	}

	@Override
	public String[] requiredColValue() {
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
	public String[] conditionColName() {
		return null;
	}

	@Override
	public String[] conditionColValue() {
		return null;
	}

	@Override
	public String getCondition() {
		return null;
	}

	@Override
	public boolean valid() {
		return statement.matches(regex.create);
	}

}
