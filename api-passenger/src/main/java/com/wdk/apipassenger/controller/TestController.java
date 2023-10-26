package com.wdk.apipassenger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-10-26
 * @Description:
 * @version: 1.0
 */
@RestController
public class TestController {
        @RequestMapping("/test")
        public String test() {
            return "test api passenger";
        }
}
