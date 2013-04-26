package Parser;

public class InsertParser extends Parser {
	String[] q1;
	String[] q2;

	public InsertParser(String q) throws Exception {
		super(q);
		String[] x = q.split("values");

		q1 = x[0].split("[ ,\\'\\=\\(\\)]+");
		q2 = x[1].split("[,\\(\\)]+");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean valid() {
		return true;
		// insert into gad (name ,age ) values ('ahmad',20); or insert into gad
		// (name) values ('ahmad');
		// there is two ways to insert
		//
		// return statement.matches(regex.insert);
	}

	@Override
	public String[] requiredColName() {
		if (q1.length > 3) {
			int r = 0;
			String[] colName = new String[q1.length - 3];
			for (int i = 3; i < q1.length; i++) {
				colName[r++] = q1[i];
			}
			return colName;
		}

		return null;
	}

	@Override
	public String[] requiredColValue() {
		String[] value = new String[q2.length];
		int k = 0;
		for (int i = 0; i < q2.length; i++) {

			String temp = q2[i];
			String s = temp.replaceAll(" ", "");
			if (s.length() == 0)
				continue;
			if (temp.contains("'")) {
				temp = temp.substring(temp.indexOf("\'"),
						temp.lastIndexOf("\'") + 1);
			} else
				temp = temp.replaceAll(" ", "");
			value[i] = temp;
			k++;
		}
		String[] colValue = new String[k];
		int r = 0;
		for (int i = 0; i < value.length; i++) {
			if (value[i] == null)
				continue;
			colValue[r++] = value[i];
		}

		return colValue;
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
	public String getCondition() {
		return null;
	}

	@Override
	public String[] getColTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
