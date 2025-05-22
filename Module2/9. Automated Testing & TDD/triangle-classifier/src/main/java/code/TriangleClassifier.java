package code;

public class TriangleClassifier {
	public static String checkTriangle(int a, int b, int c) {

		if (a + b <= c || a + c <= b || b + c <= a)
			return "Không phải tam giác";

		if (a == b && a == c)
			return "Tam giác đều";
		else if (a == b || a == c || b == c)
			return "Tam giác cân";
		else
			return "Tam giác thường";

	}
}
