package com.wdk.apipassenger.interceptor;

import com.sun.istack.internal.Interned;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Windok
 * @date: 2023-11-06
 * @Description:
 * @version: 1.0
 */
@Configuration
public class interceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                //  拦截的路径
                .addPathPatterns("/**")
                //  不拦截的路径
                .excludePathPatterns("/noauthTest");
    }
}
