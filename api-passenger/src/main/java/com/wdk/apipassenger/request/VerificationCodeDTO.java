package com.wdk.apipassenger.request;

import lombok.Data;

/**
 * @author : Windok
 * @date: 2023-10-27
 * @Description:
 * @version: 1.0
 */
@Data
public class VerificationCodeDTO {

    private String passengerPhone;
    private String verificationCode;



}
