import java.math.BigInteger;
import java.util.Scanner;

public class firstProblem {

	public static BigInteger firstMethod(String a, String b, String n) {
		
		BigInteger result = BigInteger.ONE;
		BigInteger power = new BigInteger(b);
		BigInteger mod = new BigInteger(n);
		BigInteger base = new BigInteger(a).mod(mod);
		while (!power.equals(BigInteger.ZERO)) {
			result = result.multiply(base);
			power = power.subtract(BigInteger.ONE);
		}
		return result.mod(mod);
	}

	public static BigInteger secondMethod(String a, String b, String n) {

		BigInteger result = BigInteger.ONE;
		BigInteger power = new BigInteger(b);
		BigInteger mod = new BigInteger(n);
		BigInteger base = new BigInteger(a).mod(mod);
		while (!power.equals(BigInteger.ZERO)) {
			result = result.multiply(base).mod(mod);
			power = power.subtract(BigInteger.ONE);
		}
		return result;
	}

	public static BigInteger thirdMethod(String a, String b, String n) {

		BigInteger power = new BigInteger(b);
		BigInteger mod = new BigInteger(n);
		BigInteger base = new BigInteger(a).mod(mod);
		BigInteger result = base;

		char pow[] = power.toString(2).toCharArray();
		int length = pow.length - 1;
		for (int i = 0; i < length; i++)

		{
			result = result.multiply(result).mod(mod);
			if (pow[i] == '1') {
				result = result.multiply(base).mod(mod);
			}

		}

		return result;

	}

	public static BigInteger fourthMethod(String a, String b, String n) {

		BigInteger power = new BigInteger(b);
		BigInteger mod = new BigInteger(n);
		BigInteger base = new BigInteger(a).mod(mod);
		BigInteger result = base;

		String x = power.toString(2);
		
		x = x.substring(1);
		int log = x.length() ;
		if (log == 0) {
			return (power.equals(BigInteger.ZERO) ? BigInteger.ONE : base);
		}
		for (int i = 0; i < log; i++)

		{
			result = result.multiply(result).mod(mod);

		}

		return result.multiply(fourthMethod(a, new BigInteger(x, 2).toString(), n)).mod(mod);

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		String input = "";
		while (!(input = in.nextLine()).equals("End")) {

			String values[] = input.split(" ");
			long start = System.currentTimeMillis();
			System.out.println(values[0].length());
			System.out.println(thirdMethod(values[0], values[1], values[2]));
			System.out.println((System.currentTimeMillis() - start));

		}

	}

}
