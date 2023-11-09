package com.wdk.apiboss.controller;

import com.wdk.apiboss.service.DriverUserService;
import com.wdk.internalcommon.dto.DriverUser;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-11-09
 * @Description:
 * @version: 1.0
 */
@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }

}
