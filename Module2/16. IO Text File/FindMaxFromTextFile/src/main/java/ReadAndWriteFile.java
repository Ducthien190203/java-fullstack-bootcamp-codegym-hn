import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFile {
    public List<Integer> readFile(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt((line)));
            }
            br.close();

        } catch (Exception e) {
            System.out.println("File khong ton tai hoac noi dung co loi!");

        }
        return numbers;
    }

    public void writeFile(String filePath, int max) {
        try {
            FileWriter wt = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(wt);
            bw.write("Gia tri lon nhat la:" + max);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findMax(List<Integer> numbers) {
        int max = numbers.get(0);
        for (int i = 0; i < numbers.size(); i++) {
            if (max < numbers.get(i)) {
                max = numbers.get(i);
            }
        }
        return max;
    }

}



