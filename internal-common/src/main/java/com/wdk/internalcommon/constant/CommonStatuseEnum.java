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
    //  计价规则不存在 1300 - 1399
    PRICE_RULE_EMPTY(1300, "计价规则不存在"),

    //  地图信息 1400 - 1499
    MAP_DISTRICT_ERROR(1400, "请求地图错误"),

    //  司机和车辆
    DRIVER_CAR_BINO_NOT_EXISTS(1500,"司机和车辆绑定关系不存在"),
    DRIVER_NOT_EXISTS(1501,"司机不存在"),
    DRIVER_CAR_BINO_EXISTS(1502,"司机和车辆绑定关系已存在,请勿重复绑定"),
    DRIVER_BIND_EXISTS(1503,"司机已经绑定车辆"),
    CAR_BIND_EXISTS(1504,"车辆已经绑定司机"),


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
