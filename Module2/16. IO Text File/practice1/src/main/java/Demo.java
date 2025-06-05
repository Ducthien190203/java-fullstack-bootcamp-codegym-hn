import java.io.*;

public class Demo {
    public static void main(String[] args) {
        try {
            File file=new File("test.txt");
            PrintWriter pw=new PrintWriter(new FileWriter("test.txt",true));
            pw.println(29);
            pw.println(5);
            pw.println(2025);

            pw.close();
            System.out.println("File tạo tại: " + file.getAbsolutePath());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}