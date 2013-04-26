package Parser;

public abstract class Parser {
	protected String statement;
	protected String[] tokens;
	String s2;
	String Operation;

	public Parser(String q) throws Exception {
		q = q.toLowerCase();
		statement = q;
		if(!valid())
			throw new Exception("syntax error");
		tokens = q.split("[ ,\\'\\=\\(\\)]+");
		if (q.contains("where")) {
			s2 = q.split("where")[1];
			condition();
		}
	}

	public abstract boolean valid();

	public abstract String[] requiredColName();

	public abstract String[] getColTypes();

	public abstract String[] requiredColValue();

	private String[] conditionName;
	private String[] conditionValue;

	public void condition() {
		String[] spliter;
		if (s2.contains(" and ") || s2.contains(" or ")) {

			if (s2.contains("and"))
				Operation = "and";
			else
				Operation = "or";
			spliter = s2.split("((or)|(and))");
			conditionName = new String[spliter.length];
			conditionValue = new String[spliter.length];

			for (int i = 0; i < spliter.length; i++) {
				String[] s = parse(spliter[i], 0);
				conditionName[i] = s[0];
				conditionValue[i] = s[1];
			}

		} else if (s2.contains(">=") || s2.contains("<=")) {

			if (s2.contains(">="))
				Operation = ">=";
			else
				Operation = "<=";

			String[] s = parse(s2, 1);
			conditionName = new String[] { s[0] };
			conditionValue = new String[] { s[1] };
		} else if (s2.contains(">") || s2.contains("<")) {

			if (s2.contains(">"))
				Operation = ">";
			else
				Operation = "<";

			String[] s = parse(s2, 2);
			conditionName = new String[] { s[0] };
			conditionValue = new String[] { s[1] };

		} else {
			Operation = "and";
			String[] s = parse(s2, 0);
			conditionName = new String[] { s[0] };
			conditionValue = new String[] { s[1] };
		}
	}

	private String[] parse(String s, int x) {
		String name, value;
		String[] s1;
		if (x == 0)
			s1 = s.split("[=]+");
		else if (x == 1)
			s1 = s.split("[(>=)|(<=)]+");
		else
			s1 = s.split("[>|<]+");
		name = s1[0].replaceAll(" ", "");
		if (!s1[1].contains("\'"))
			value = s1[1].replace(" ", "");
		else
			value = s1[1].substring(s1[1].indexOf("\'"),
					s1[1].lastIndexOf("\'") + 1);

		return new String[] { name, value };
	}

	public String[] conditionColName() {
		return conditionName;
	}

	public String[] conditionColValue() {
		return conditionValue;
	}

	public abstract String getTableName();

	public String getQueryType() {
		return tokens[0];
	}

	public String getCondition() {
		return Operation;
	}

}