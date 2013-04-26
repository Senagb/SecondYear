package DBMS;


public class Search {
	protected String[] conditionColName;
	protected String[] conditionColValues;
	protected String condition;
	private Table table;
	protected boolean run = false;
	int [] result;
	int index;
	public Search(Table tt, String[] RCN, String[] RCV, String Con) {
		// TODO Auto-generated constructor stub
		conditionColName = RCN;
		conditionColValues = RCV;
		table=tt;
		condition = Con;
		result = new int[tt.getLength()];
		index=0;
	}

	// this method to return the number of column that matches the condition
	// entered by the user
	public int[] rowGetter() {
		run = true;
		if (condition.equals("and")) {
			for (int i = 1; i < table.getLength(); i++) {
				boolean check = true;
				for (int j = 0; j < conditionColName.length && check; j++) {
					int col = table.getColumnIndex(conditionColName[j]);
					if (!table.getValue(i, col).equals(conditionColValues[j])) {
						check = false;
					}
				}
				if (check) {
					result[index++]= i;
				}

			}

		} else if (condition.equals("or")) {
			for (int i = 1; i < table.getLength(); i++) {
				boolean check = true;
				for (int j = 0; j < conditionColName.length && check; j++) {
					int col = table.getColumnIndex(conditionColName[j]);
					if (table.getValue(i, col).equals(conditionColValues[j])) {
						check = false;
					}
				}
				if (!check) {
					result[index++]= i;
				}
			}
		} else {
			findtable(condition, Double.parseDouble(conditionColValues[0]),
					table.getColumnIndex(conditionColName[0]));
		}
		int[] res = new int[index];
		for (int i = 0; i < index; i++) {
			res[i] = result[i];
		}

		return res;
	}

	public void findtable(String cond, double x, int col) {
		for (int i = 1; i < table.getLength(); i++) {
			String number =table.getValue(i, col);
			if(number.length()!=0)
			{
				Double y = Double.parseDouble(number);
				if (oper(cond, x, y))
					result[index++]= i;
		}
		}

	}

	public boolean oper(String cond, double one, double two) {
		if (cond.equals(">"))
			return (one < two);
		else if (cond.equals("<"))
			return (one > two);
		else if (cond.equals(">="))
			return (one <= two);
		else if (cond.equals("<="))
			return (one >= two);
		return false;

	}
}
