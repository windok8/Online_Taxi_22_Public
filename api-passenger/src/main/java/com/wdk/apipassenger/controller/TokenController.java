package com.wdk.apipassenger.controller;

import com.wdk.apipassenger.service.TokenService;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;
    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse) {
        String refreshToken = tokenResponse.getRefreshToken();
        System.out.println("refreshToken = " + refreshToken);
        return tokenService.refreshToken(refreshToken);
    }

}
