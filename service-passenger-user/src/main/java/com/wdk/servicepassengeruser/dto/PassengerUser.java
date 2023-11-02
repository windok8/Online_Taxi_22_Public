package com.wdk.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : Windok
 * @date: 2023-11-02
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

}
