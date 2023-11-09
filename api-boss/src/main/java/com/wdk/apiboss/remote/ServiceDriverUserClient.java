package com.wdk.apiboss.remote;

import com.wdk.internalcommon.dto.DriverUser;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : Windok
 * @date: 2023-11-09
 * @Description:
 * @version: 1.0
 */
@FeignClient(name = "service-driver-user")
public interface ServiceDriverUserClient {

    /**
     * @Author: Windok
     * @Description:    添加司机用户
     * @param driverUser
     * @return ResponseResult
     **/
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser);
    /**
     * @Author: Windok
     * @Description:    修改司机用户
     * @param driverUser
     * @return ResponseResult
     **/
    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    public ResponseResult updateDriverUser(DriverUser driverUser);


}