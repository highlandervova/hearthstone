package enums;

public enum RedirectPath {
    HEAD_PATH ("hearthstone_war"),
    MAIN_PAGE("/hearthstone_war/main"),
    MAIN_REDIRECT("main"),
    LOGIN_PAGE("/hearthstone_war/auth"),
    REG_PAGE("/hearthstone_war/reg"),
    EDIT_USER("/hearthstone_war/editUser");
    //UPLOAD_PAGE("/olx_war/uploadAd");


    private RedirectPath(String value) {
        this.value = value;
    }
    private String value;
    public String getValue() {
        return value;
    }
}



