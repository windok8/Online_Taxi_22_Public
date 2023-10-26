package com.wdk.apipassenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : Windok
 * @date: 2023-10-26
 * @Description:
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiPassengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPassengerApplication.class);

    }

}
