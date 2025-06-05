import java.io.*;

public class Demo2 {
    public static void main(String[] args) {
        //ghi dữ liệu vào file temp.dat với DataOutputStream
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream("D:\\java-fullstack-bootcamp-codegym-hn\\Module2\\17. IO Binary File & Serialization\\Demo\\DataStream.txt"))) {
            output.writeUTF("John");
            output.writeDouble(85.5);
            output.writeUTF("Susan");
            output.writeDouble(185.5);
            output.writeUTF("Kim");
            output.writeDouble(105.25);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //đọc dữ liệu từ file temp.dat với DataInputStream
        try (DataInputStream input = new DataInputStream(new FileInputStream("D:\\java-fullstack-bootcamp-codegym-hn\\Module2\\17. IO Binary File & Serialization\\Demo\\DataStream.txt"))) {
            System.out.println(input.readUTF() + " " + input.readDouble());
            System.out.println(input.readUTF() + " " + input.readDouble());
            System.out.println(input.readUTF() + " " + input.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
