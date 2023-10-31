package com.wdk.apipassenger.service;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import com.wdk.apipassenger.remote.ServiceVerificationcodeClient;
import com.wdk.internalcommon.constant.CommonStatuseEnum;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.dto.TokenResponse;
import com.wdk.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
        System.out.println("numberCode = " + numberCode);
        //  存入 Redis
        System.out.println("存入 Redis");
        //  key, value, 过期时间
        String key = createKeyByPhone(passengerPhone);
        //  存入 Redis
        stringRedisTemplate.opsForValue().set(key, numberCode+ "", 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到对应的手机上，阿里短信服务，腾讯短信服务，华信短信服务，容联云通讯

        return ResponseResult.success();
    }

    /**
     * @Author: Windok
     * @Description:    根据手机号生成 key
     * @param passengerPhone  手机号
     * @return String
     **/
    private String createKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }


    /**
     * @Author: Windok
     * @Description:    校验验证码
     * @param passengerPhone    手机号
     * @param verificationCode  验证码
     * @return ResponseResult
     **/
    public ResponseResult checkCode(String passengerPhone,String verificationCode) {
        //  根据手机号从 Redis 中取出验证码
        //  生成 key
        String key = createKeyByPhone(passengerPhone);
        //  获取 value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("codeRedis = " + codeRedis);
        //  校验验证码
        System.out.println("校验验证码");
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatuseEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatuseEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if(!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatuseEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatuseEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        //  判断用户是否存在，如果存在，直接返回用户信息，如果不存在，先注册，后返回用户信息
        System.out.println("判断用户是否存在，如果存在，直接返回用户信息，如果不存在，先注册，后返回用户信息");

        //  颁发 token
        System.out.println("颁发 token");

        //  响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
}
