package app;

import shape.Circle;

public class CircleTest {
	public static void main(String[] args) {
		Circle circle1 = new Circle();
		System.out.println(circle1.toString());

		circle1 = new Circle(4);
		System.out.println(circle1.toString());

		circle1 = new Circle(6, "blue");
		System.out.println(circle1.toString());

	}
}
