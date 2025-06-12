package geometry;

public class Shape {
    private String color = "green";
    private boolean filled = true;

    public Shape() {
        super();
    }

    public Shape(String colorString, boolean filled) {
        super();
        this.color = colorString;
        this.filled = filled;
    }

    public String getColorString() {
        return color;
    }

    public void setColorString(String colorString) {
        this.color = colorString;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "color='" + color + '\'' +
                ", filled=" + filled +
                '}';
    }
}
