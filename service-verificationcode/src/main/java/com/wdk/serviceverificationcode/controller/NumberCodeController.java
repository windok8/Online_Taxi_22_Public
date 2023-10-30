package com.wdk.serviceverificationcode.controller;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-10-27
 * @Description:
 * @version: 1.0
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size) {
        System.out.println("size = " + size);
        //  生成验证码
        double mathRandom = (Math.random() * 9 + 1) * (Math.pow(10, size - 1));
        int resultInt = (int) mathRandom;
        System.out.println("generator src code:" + resultInt);

        //  定义返回值
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        //return result.toString();
        return ResponseResult.success(response);
    }


}
