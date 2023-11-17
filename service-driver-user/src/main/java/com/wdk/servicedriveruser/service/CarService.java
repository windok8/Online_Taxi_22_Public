package com.wdk.servicedriveruser.service;

import com.wdk.internalcommon.dto.Car;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.servicedriveruser.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author : Windok
 * @date: 2023-11-10
 * @Description:
 * @version: 1.0
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public ResponseResult addCar(Car car) {
        LocalDateTime now = LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        carMapper.insert(car);
        return ResponseResult.success("");
    }

}
