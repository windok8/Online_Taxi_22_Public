package com.wdk.apiboss.service;

import com.wdk.apiboss.remote.ServiceDriverUserClient;
import com.wdk.internalcommon.dto.Car;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-11-11
 * @Description:
 * @version: 1.0
 */
@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addcar(Car car){
        return serviceDriverUserClient.addcar(car);
    }

}
