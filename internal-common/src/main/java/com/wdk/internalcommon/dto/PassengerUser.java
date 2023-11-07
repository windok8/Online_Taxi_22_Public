package com.wdk.internalcommon.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@Data
public class PassengerUser {


    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private String passengerPhone;
    private String passengerName;
    private Byte passengerGender;
    private Byte state;
    private String profilePhoto;

}
