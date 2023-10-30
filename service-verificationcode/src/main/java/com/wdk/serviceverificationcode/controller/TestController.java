package com.wdk.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-10-27
 * @Description:
 * @version: 1.0
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "service-verificationcode\t";
    }

}
