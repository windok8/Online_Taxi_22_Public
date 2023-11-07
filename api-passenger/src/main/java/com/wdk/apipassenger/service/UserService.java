package com.wdk.apipassenger.service;

import com.wdk.apipassenger.remote.ServicePassengerUserClient;
import com.wdk.internalcommon.dto.PassengerUser;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.dto.TokenResult;
import com.wdk.internalcommon.request.VerificationCodeDTO;
import com.wdk.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken) {
        log.info("accessToken: " + accessToken);
        //  解析 accessToken 取得手机号
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("phone: " + phone);
        //  根据手机号查询用户信息

        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone.getData());
    }

}
