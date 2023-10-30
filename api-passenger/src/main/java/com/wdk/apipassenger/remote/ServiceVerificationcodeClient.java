package com.wdk.apipassenger.remote;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.response.NumberCodeResponse;
import lombok.experimental.FieldNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author : Windok
 * @date: 2023-10-30
 * @Description:
 * @version: 1.0
 */
@FeignClient(name = "service-verificationcode")
public interface ServiceVerificationcodeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);
}
