package com.wdk.apipassenger.controller;

import com.wdk.apipassenger.request.VerificationCodeDTO;
import com.wdk.apipassenger.service.VerificationCodeService;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Windok
 * @date: 2023-10-27
 * @Description:
 * @version: 1.0
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@Validated @RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        return  verificationCodeService.generatorCode(passengerPhone);

    }

}
