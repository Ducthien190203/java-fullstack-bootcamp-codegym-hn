import java.io.*;

public class OverrideTextFile {
    public static void overrideTextFile(String filePath) throws FileNotFoundException {

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("19");
            bw.newLine();
            bw.write("02");
            bw.newLine();
            bw.write("2003");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File text da duoc ghi de");
    }
}
