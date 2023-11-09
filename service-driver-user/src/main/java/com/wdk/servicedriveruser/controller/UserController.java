package com.wdk.servicedriveruser.controller;

import com.wdk.internalcommon.dto.DriverUser;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.servicedriveruser.service.DriverUserServices;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
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
@Slf4j
public class UserController {


    @Autowired
    private DriverUserServices driverUserServices;

    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){

        log.info(JSONObject.fromObject(driverUser).toString());

        return driverUserServices.addDriverUser(driverUser);
    }

}
