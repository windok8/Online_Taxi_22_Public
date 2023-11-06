package com.wdk.apipassenger.controller;

import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Struct;

/**
 * @author : Windok
 * @date: 2023-10-27
 * @Description:
 * @version: 1.0
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "test api passenger";
    }

    /**
     * @Author: Windok
     * @Description:    需要有token才能访问的接口
     * @Date: 2023-10-27
     * @return ResponseResult
     **/
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("authTest");
    }

    /**
     * @Author: Windok
     * @Description:    不需要token就能访问的接口
     * @Date: 2023-10-27
     * @return ResponseResult
     **/
    @GetMapping("/noauthTest")
    public ResponseResult noauthTest(){
        return ResponseResult.success("noauthTest");
    }
}
