package com.wdk.servicepassengeruser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : Windok
 * @date: 2023-10-31
 * @Description:
 * @version: 1.0
 */
@SpringBootApplication
@EnableFeignClients // 开启feign
@MapperScan("com.wdk.servicepassengeruser.mapper")
public class ServicePassengerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePassengerUserApplication.class);
    }

}
