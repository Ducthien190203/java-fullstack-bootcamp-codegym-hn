public class ValidatePhoneNumber {
    private static final String PHONE_NUMBER_REGEX = "^\\(\\d{2}\\)-\\(0\\d{9}\\)$";

    public ValidatePhoneNumber() {
    }

    public static boolean validate(String regex) {
        if (regex == null) {
            return false;
        }
        return regex.matches(PHONE_NUMBER_REGEX);
    }

}
