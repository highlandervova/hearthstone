package enums;

public enum SessionAttribute {

    AUTHENTICATED("authenticated");

    private SessionAttribute(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}