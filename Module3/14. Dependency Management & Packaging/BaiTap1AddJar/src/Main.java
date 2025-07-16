import vn.codegym.QuadraticSolver;

public class Main {
    public static void main(String[] args) {
        // Ví dụ giải phương trình x^2 - 3x + 2 = 0
        double a = 1, b = -3, c = 2;

        String result = QuadraticSolver.solve(a, b, c);
        System.out.println(result);
    }
}
