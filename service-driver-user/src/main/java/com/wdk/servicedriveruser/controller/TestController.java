package com.wdk.servicedriveruser.controller;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.servicedriveruser.service.DriverUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-11-09
 * @Description:
 * @version: 1.0
 */
@RestController
public class TestController {

    @Autowired
    private DriverUserServices driverUserServices;

    @GetMapping("/test-db")
    public ResponseResult testDb() {
        return driverUserServices.testGetDriverUser();
    }

}
