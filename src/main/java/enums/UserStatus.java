package enums;

public enum UserStatus {

    CHANGES_SAVED("Changes have been saved!"),
    CHANGES_WITH_PASSWORD_OK("Changes have been saved!"),
    PASSWORD_INCORRECT("Current password is incorrect."),
    PASSWORD_FIELDS_MISMATCH("New password fields doesn't match."),
    ENTER_PASSWORD("Please enter password.");

    private UserStatus(String value) {
        this.value = value;
    }
    private String value;
    public String getValue() {
        return value;
    }

}
