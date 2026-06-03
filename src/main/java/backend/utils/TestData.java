package backend.utils;

public class TestData {

    public static String validFirstName() {
        return "John";
    }

    public static String validLastName() {
        return "Doe";
    }

    public static String validPhone() {
        return "+250789000000";
    }

    public static String updatedFirstName() {
        return "Jane";
    }

    public static String updatedPhone() {
        return "+250791376494";
    }

    public static String profileBody(String firstName, String lastName, String phone) {
        return String.format("""
                {
                  "firstName": "%s",
                  "lastName": "%s",
                  "phone": "%s"
                }
                """,
                firstName,
                lastName,
                phone
        );
    }
}