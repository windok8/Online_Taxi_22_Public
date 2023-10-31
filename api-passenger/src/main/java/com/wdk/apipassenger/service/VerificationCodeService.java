package com.wdk.apipassenger.service;

import com.wdk.apipassenger.remote.ServiceVerificationcodeClient;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : Windok
 * @date: 2023-10-27
 * @Description:
 * @version: 1.0
 */
@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String verificationCodePrefix = "passenger-verification-code-";

    public ResponseResult generatorCode(String passengerPhone) {
        //  调用验证码服务生成验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        //  存入 Redis
        System.out.println("存入 Redis");
        //  key, value, 过期时间
        String key = verificationCodePrefix + passengerPhone;
        //  存入 Redis
        stringRedisTemplate.opsForValue().set(key, numberCode+ "", 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到对应的手机上，阿里短信服务，腾讯短信服务，华信短信服务，容联云通讯

        return ResponseResult.success();
    }

}
