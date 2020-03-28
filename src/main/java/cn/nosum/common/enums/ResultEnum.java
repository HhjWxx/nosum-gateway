package cn.nosum.common.enums;

public enum ResultEnum {
    SUCCESS(1, "成功"),
    FAILING(0, "失败"),
    URL_EXCLUDE(2,"无效的URL")
    ;
    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}