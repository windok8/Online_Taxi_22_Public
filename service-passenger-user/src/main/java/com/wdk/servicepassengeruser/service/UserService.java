package com.wdk.servicepassengeruser.service;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Windok
 * @date: 2023-10-31
 * @Description:
 * @version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhoen) {
        System.out.println("User Service 被调了，手机号是：" + passengerPhoen);
        //  根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhoen);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size() == 0? "无记录" : passengerUsers.get(0).getPassengerPhone());
        //  判断用户是否存在
        if(passengerUsers.size() == 0){
            //  如果不存在，插入用户信息
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("windok");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhoen);
            passengerUser.setState((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);


            passengerUserMapper.insert(passengerUser);
        }
        //  如果不存在，插入用户信息
        return ResponseResult.success();
    }

}
