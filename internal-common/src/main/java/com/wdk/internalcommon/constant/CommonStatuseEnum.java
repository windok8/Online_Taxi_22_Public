package com.wdk.internalcommon.constant;

/**
 * @author : Windok
 * @date: 2023-10-30
 * @Description:
 * @version: 1.0
 */
public enum CommonStatuseEnum {

    //  验证码错误   1000 - 1099
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),

    //  Token 类提示 1100 - 1199
    TOKEN_ERROR(1199, "Token 错误"),

    //  用户类提示 1200 - 1299
    USER_NOT_EXIST(1200, "当前用户不存在"),

    //  成功
    SUCCESS(1, "success"),
    //  失败
    FAIL(0, "fail");

    private int code;
    private String value;

    CommonStatuseEnum(int i, String messge) {
        this.code = i;
        this.value = messge;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
