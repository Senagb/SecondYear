package Parser;


public class UpdateParser extends Parser {

	public UpdateParser(String q) throws Exception {
		super(q);
		required();
	}

	String[] reqColName;
	String[] reqcolValue;

	public void required() {
		int i1 = statement.indexOf("set") + 3;
		int i2 = statement.indexOf("where");
		if(i2 == -1)
			i2 = statement.length();
		String[] temp = statement.substring(i1, i2).split(",");

		reqColName = new String[temp.length];
		reqcolValue = new String[temp.length];

		for (int i = 0; i < temp.length; i++) {
			String[] x = parse(temp[i]);
			reqColName[i] = x[0];
			reqcolValue[i] = x[1];
		}
	}

	private String[] parse(String s) {
		String name, value;
		String[] s1;
		s1 = s.split("[=]+");
		name = s1[0].replace(" ", "");
		if (!s1[1].contains("\'"))
			value = s1[1].replace(" ", "");
		else
			value = s1[1].substring(s1[1].indexOf("\'"),
					s1[1].lastIndexOf("\'") + 1);
		return new String[] { name, value };
	}

	@Override
	public String[] requiredColName() {
		return reqColName;
	}

	@Override
	public String[] requiredColValue() {
		return reqcolValue;
	}

	@Override
	public String getTableName() {
		return tokens[1];
	}

	@Override
	public boolean valid() {
		// return statement.matches(regex.update);
		// there are 4 ways to select
		//
		return true;
	}

	@Override
	public String[] getColTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
