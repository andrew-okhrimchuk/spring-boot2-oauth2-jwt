package top.okhrimchuk.decompose.web.exeption;

public class SimpleErrorModel {

    private String errorMessage;
    private String moreInfo;

    public SimpleErrorModel(String errorMessage) {
        this(errorMessage, "");
    }

    public SimpleErrorModel(String errorMessage, String moreInfo) {
        this.errorMessage = errorMessage;
        this.moreInfo = moreInfo;
    }
}