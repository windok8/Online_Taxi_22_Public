package com.wdk.apidriver.service;

import com.wdk.apidriver.remote.ServiceDriverUserClient;
import com.wdk.internalcommon.dto.DriverUser;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-11-09
 * @Description:
 * @version: 1.0
 */
@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult updateUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateUser(driverUser);
    }

}
