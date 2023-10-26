package com.wdk.apipassenger.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-10-26
 * @Description:
 * @version: 1.0
 */
@Service
public class VerificationCodeService {
    public String generatorCode(String passengerPhone) {
        //  调用验证码服务生成验证码
        System.out.println("调用验证码服务生成验证码");
        String code = "888888";
        //  存入 Redis
        System.out.println("存入 Redis");
        //  返回值
        JSONObject result = new JSONObject();
        result.put("code", 1);
        result.put("message", "success");
        return result.toString();
    }

}
