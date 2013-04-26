public class CRT {
	static int length;
	static int M;
	static int Mi[];
	static int MiInverse[];
	static int ai[];
	static int bi[];

	public static void CRTAddition(int[] mi, int A, int B) {
		length = mi.length;
		M = 1;
		Mi = new int[length];
		MiInverse = new int[length];
		ai = new int[length];
		bi = new int[length];
		for (int i = 0; i < length; i++) {
			M *= mi[i];

		}

		for (int i = 0; i < length; i++) {
			Mi[i] = M / mi[i];
			System.out.println(Mi[i]);
			MiInverse[i] = extendEculidsTheorem.calculate(Mi[i],mi[i] );
			while(MiInverse[i]<0){MiInverse[i]+=mi[i];}
			System.out.println(MiInverse[i]);
			ai[i] = A % mi[i];
			bi[i] = B % mi[i];
		}

	}

	public static int Addition(int A, int B) {

		return (A + B) % M;
	}

	public static int Addition() {
		int result = 0;

		for (int i = 0; i < length; i++) {
			result += (ai[i] + bi[i]) * Mi[i] * MiInverse[i];
		}
		return result % M;
	}

	public static void main(String[] args) {
		CRTAddition(new int[] { 37, 49 }, 6, 8);
//		System.out.println(
				Addition();
		System.out.println(Addition(6, 8));
	}

}
