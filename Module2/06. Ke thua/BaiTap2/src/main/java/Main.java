public class Main {
    public static void main(String[] args) {
        Point2D point2D = new Point2D(3.5f, 4.5f);
        for (float value : point2D.getXY()) {
            System.out.println("Tọa độ: " + value);
        }
        System.out.println("--------------------");
        Point3D point3D = new Point3D(1.0f, 2.0f, 3.0f);
        for (float value : point3D.getXYZ()) {
            System.out.println("Tọa độ: " + value);
        }

    }
}
