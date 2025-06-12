public class QuadraticEquation {
        private double a,b,c;

    private QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public static QuadraticEquation createQuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Coefficient 'a' cannot be zero for a quadratic equation.");
        }
        return new QuadraticEquation(a, b, c);
    }

    private double getA() {
        return a;
    }

    private void setA(double a) {
        this.a = a;
    }

    private double getB() {
        return b;
    }

    private void setB(double b) {
        this.b = b;
    }

    private double getC() {
        return c;
    }

    private void setC(double c) {
        this.c = c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }
    public double getRoot1() {
        return (-b + Math.sqrt(getDiscriminant())) / (2 * a);
    }
    public double getRoot2() {
        return (-b - Math.sqrt(getDiscriminant())) / (2 * a);
    }
}
