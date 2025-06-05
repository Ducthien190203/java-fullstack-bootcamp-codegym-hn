import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();
        List<Integer> numbers = readAndWriteFile.readFile("D:\\Dev\\567Abc.txt");
        int maxValue = ReadAndWriteFile.findMax(numbers);
        readAndWriteFile.writeFile("D:\\Dev\\result.txt", maxValue);
    }
}
