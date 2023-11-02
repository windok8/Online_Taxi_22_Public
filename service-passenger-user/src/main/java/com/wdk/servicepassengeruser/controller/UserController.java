package com.wdk.servicepassengeruser.controller;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.request.VerificationCodeDTO;
import com.wdk.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-10-31
 * @Description:
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhoen = verificationCodeDTO.getPassengerPhone();
        System.out.println("passengerPhoen = " + passengerPhoen);
        return userService.loginOrRegister(passengerPhoen);
    }

}
