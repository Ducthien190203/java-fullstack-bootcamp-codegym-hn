public class NameClassExample {
    private static final String NAME_CLASS_REGEX = "^[CAP]\\d{4}[GHIK]$";

    public NameClassExample() {
    }

    public static boolean validate(String regex) {
        if (regex == null) {
            return false;
        }
        return regex.matches(NAME_CLASS_REGEX);
    }

}
