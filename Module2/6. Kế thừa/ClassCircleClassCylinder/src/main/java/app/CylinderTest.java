package app;

import shape.Cylinder;

public class CylinderTest {
	public static void main(String[] args) {
		Cylinder cylinder1 = new Cylinder();
		System.out.println(cylinder1.toString());

		cylinder1 = new Cylinder(2.0, 5.0, "purple");

		System.out.println(cylinder1.toString());

	}
}
