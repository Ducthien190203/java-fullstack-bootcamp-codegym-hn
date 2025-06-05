public class Main {

    public static void main(String[] args) {
        // write your code here
        String s1 = "java";//Tạo chuỗi bằng string literal
        char ch[] = { 's', 't', 'r', 'i', 'n', 'g', 's' };
        String s2 = new String(ch);//convert mảng char thành chuỗi
        String s3 = new String("example");//Tạo chuỗi bằng từ khóa new
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}