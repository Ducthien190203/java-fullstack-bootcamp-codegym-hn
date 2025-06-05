import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExample {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean validate(String regex) {
        if (regex == null) return false; // kiểm tra null để tránh lỗi
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
