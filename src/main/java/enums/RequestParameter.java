package enums;


public enum RequestParameter {
    LOGIN           ("login"),
    PASS            ("pass"),
    PASS1           ("pass1"),
    PASS2           ("pass2"),
    LASTNAME        ("lastname"),
    PHONE           ("phone"),
    EMAIL           ("email"),
    NAME            ("name"),
    DESCR       ("descr"),
    PICTYPE         ("pictype"),
    RACE            ("race"),
    RUBRIC      ("rubric"),
    IDAD        ("idAd"),
    TYPE        ("type"),
    CITYSEARCH  ("citysearch"),
    USERADSSEARCH ("usersearch"),
    RUBRICSEARCH ("rubricsearch"),
    NAMESEARCH   ("namesearch"),
    FAVORSEARCH  ("favorsearch"),
    DESCRSEARCH ("descrsearch"),
    UPLOADFILE   ("uploadfile"),
    REDIRECTS    ("redirects"),
    LOGOFF       ("logoff");

    RequestParameter(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
