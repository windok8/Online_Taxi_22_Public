package com.wdk.internalcommon.util;

/**
 * @author : Windok
 * @date: 2023-11-06
 * @Description:
 * @version: 1.0
 */
public class RedisPrefixUtils {

    //  验证码前缀
    public static String verificationCodePrefix = "passenger-verification-code-";
    //  token 前缀
    public static String tokenPrefix = "token-";

    /**
     * @param passengerPhone 手机号
     * @return String
     * @Author: Windok
     * @Description: 根据手机号生成 key
     **/
    public static String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * @Author: Windok
     * @Description: 根据手机号和身份生成 token 的 key
     * @param phone
     * @param identity
     * @return String
     **/
    public static String generatorTokenKey(String phone,String identity, String tokenType) {
        return tokenPrefix + phone + "-" + identity + "-" + tokenType;
    }


}
