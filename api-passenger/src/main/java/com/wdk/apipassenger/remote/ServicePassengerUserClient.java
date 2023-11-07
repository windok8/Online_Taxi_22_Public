package com.wdk.apipassenger.remote;

import com.wdk.internalcommon.dto.PassengerUser;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : Windok
 * @date: 2023-11-05
 * @Description:
 * @version: 1.0
 */
@FeignClient(name = "service-passenger-user")
public interface ServicePassengerUserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    @RequestMapping(method = RequestMethod.GET, value = "/user/{phone}")
    public ResponseResult<PassengerUser> getUserByPhone(@PathVariable("phone") String passengerPhoen);

}
