package com.wdk.servicedriveruser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;

/**
 * @author : Windok
 * @date: 2023-11-09
 * @Description:    代码生成器
 * @version: 1.0
 */
public class MysqlGenerator {




    public static void main(String[] args) {


        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8",
                "root","123456")
                .globalConfig(
                        builder ->{
                            builder.author("Windok").fileOverride().outputDir("F:\\Dev_Demo\\OnlineTaxi_Demo\\service-driver-user\\src\\main\\java");
                        }
                ).packageConfig(
                        builder ->{
                            builder.parent("com.wdk.servicedriveruser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                    "F:\\Dev_Demo\\OnlineTaxi_Demo\\service-driver-user\\src\\main\\java\\com\\wdk\\servicedriveruser\\mapper"));

                        }
                ).strategyConfig(builder -> {
                    builder.addInclude("car");
                }).templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
