/**
 * 
 */
package geometry;

/**
 * 
 */
public class Rectangle extends Shape {
	private double width = 1.0;
	private double length = 1.0;

	public Rectangle() {
		super();
	}

	public Rectangle(double width, double height) {
		super();
		this.width = width;
		this.length = height;
	}

	public Rectangle(double width, double height, String color, boolean filled) {
		super(color, filled);
		this.width = width;
		this.length = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double height) {
		this.length = height;
	}

	@Override
	public String toString() {
		return "Rectangle [getWidth()=" + getWidth() + ", getLength()=" + getLength() + ", super class="
				+ super.toString() + "]";
	}

}
