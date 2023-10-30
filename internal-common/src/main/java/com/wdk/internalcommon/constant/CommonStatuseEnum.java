package com.wdk.internalcommon.constant;

/**
 * @author : Windok
 * @date: 2023-10-30
 * @Description:
 * @version: 1.0
 */
public enum CommonStatuseEnum {

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
