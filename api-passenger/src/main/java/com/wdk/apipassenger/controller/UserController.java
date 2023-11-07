package com.wdk.apipassenger.controller;

import com.wdk.apipassenger.service.UserService;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request) {
        //  从 http 请求中获取 accessToken
        String accessTonken = request.getHeader("Authorization");

        return userService.getUserByAccessToken(accessTonken);
    }

}
