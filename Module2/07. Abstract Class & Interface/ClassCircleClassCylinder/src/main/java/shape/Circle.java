package shape;

public class Circle {
    private double radius = 1.0;
    private String color = "red";

    public Circle() {
        super();
    }

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public Circle(double radius, String color) {
        super();
        this.radius = radius;
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;

    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle [getRadius()=" + getRadius() + ", getColor()=" + getColor() + ", getPerimeter()="
                + getPerimeter() + ", getArea()=" + getArea() + "]";
    }

}
