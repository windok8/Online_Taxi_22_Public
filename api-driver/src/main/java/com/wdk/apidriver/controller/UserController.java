package com.wdk.apidriver.controller;

import com.wdk.apidriver.remote.ServiceDriverUserClient;
import com.wdk.apidriver.service.UserService;
import com.wdk.internalcommon.dto.DriverUser;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-11-09
 * @Description:
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {
        return userService.updateUser(driverUser);
    }

}
