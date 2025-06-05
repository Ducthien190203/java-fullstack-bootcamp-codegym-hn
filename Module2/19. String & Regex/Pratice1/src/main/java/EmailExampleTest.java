public class EmailExampleTest {
    public static final String[] validEmail = {
            "a@gmail.com",
            "thien@gmail.com",
            "nguyen@hotmail.com"
    };

    public static final String[] invalidEmail = {
            "gmail.com",
            "@gmail.com",
            "nguyen@hotmail"
    };

    public static void main(String[] args) {
        System.out.println("=== Valid Emails ===");
        for (String email : validEmail) {
            boolean isValid = EmailExample.validate(email);
            System.out.println("Email: " + email + " → Valid: " + isValid);
        }

        System.out.println("\n=== Invalid Emails ===");
        for (String email : invalidEmail) {
            boolean isValid = EmailExample.validate(email);
            System.out.println("Email: " + email + " → Valid: " + isValid);
        }
    }
}
