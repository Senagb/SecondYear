import java.util.Arrays;

public class extendEculidsTheorem {
	static boolean no = false;

	public static int extend(int[] a, int[] b) {

		if (b[2] == 0) {
			no = true;
			return -1;

		} else if (b[2] == 1)
			return b[1];
		else {
			int q = (int) Math.floor(a[2] / b[2]);
			int[] t = new int[3];
			t[0] = a[0] - (q * b[0]);
			t[1] = a[1] - (q * b[1]);
			t[2] = a[2] - (q * b[2]);

			for (int i = 0; i < t.length; i++) {
				a[i] = b[i];
				b[i] = t[i];
			}
			return extend(a, b);
		}

	}

	public static boolean isNo() {
		return no;
	}

	public static void setNo(boolean no) {
		extendEculidsTheorem.no = no;
	}

	public static int calculate(int m, int b) {
		no = false;
		return extend(new int[] { 1, 0, m }, new int[] { 0, 1, b });
	}
public static void main(String[] args) {

System.out.println();}
}
