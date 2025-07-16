package vn.codegym;

public class QuadraticSolver {

    public static String solve(double a, double b, double c) {
        if (a == 0) {
            return "Không phải phương trình bậc 2.";
        }

        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            return "Phương trình vô nghiệm.";
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Phương trình có nghiệm kép x = " + x;
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Phương trình có 2 nghiệm x1 = " + x1 + ", x2 = " + x2;
        }
    }
}
