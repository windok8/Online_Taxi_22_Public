package com.wdk.apipassenger.service;

import com.wdk.internalcommon.constant.CommonStatuseEnum;
import com.wdk.internalcommon.constant.TokenConstants;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.dto.TokenResponse;
import com.wdk.internalcommon.dto.TokenResult;
import com.wdk.internalcommon.util.JwtUtils;
import com.wdk.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokensrc) {
        //  解析 refreshToken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokensrc);
        if (tokenResult == null) {
            return ResponseResult.fail(CommonStatuseEnum.TOKEN_ERROR.getCode(), CommonStatuseEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
        //  从 redis 中获取 refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        System.out.println("refreshTokenKey = " + refreshTokenKey);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        //  校验 refreshToken
        if ((StringUtils.isBlank(refreshTokenRedis)) || (!refreshTokensrc.trim().equals(refreshTokenRedis.trim()))) {
            return ResponseResult.fail(CommonStatuseEnum.TOKEN_ERROR.getCode(), CommonStatuseEnum.TOKEN_ERROR.getValue());
        }
        //  生成新的 token
        String refreshToken = JwtUtils.generatorToken(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
        //  将新的 refreshToken 和 accessToken 存入 redis
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);



        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);


        return ResponseResult.success(tokenResponse);
    }

}
