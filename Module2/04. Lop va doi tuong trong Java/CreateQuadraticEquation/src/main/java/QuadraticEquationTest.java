public class QuadraticEquationTest {
    public static void main(String[] args) {
//        try {
//            QuadraticEquation equation = QuadraticEquation.createQuadraticEquation(1, -3, 2);
//            System.out.println("Quadratic equation created successfully with coefficients: a=1, b=-3, c=2");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }

//        try {
//            QuadraticEquation equation = QuadraticEquation.createQuadraticEquation(0, 2, 1);
//            System.out.println("Quadratic equation created successfully with coefficients: a=0, b=2, c=1");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
        try {
            QuadraticEquation equation = QuadraticEquation.createQuadraticEquation(4, 5, 6);
            System.out.println("Discriminant: " + equation.getDiscriminant());
            System.out.println("Root 1: " + equation.getRoot1());
            System.out.println("Root 2: " + equation.getRoot2());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
