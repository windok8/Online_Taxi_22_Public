package com.wdk.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.dto.TokenResult;
import com.wdk.internalcommon.util.JwtUtils;
import com.wdk.internalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author : Windok
 * @date: 2023-11-06
 * @Description:
 * @version: 1.0
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //  从请求头中获取token
        boolean result = true;
        String resultString = "";
        String token = request.getHeader("Authorization");
        //  解析 Token
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {
            //  token签名错误
            resultString = "Token sign error ";
            result = false;
        } catch (TokenExpiredException e) {
            //  token过期
            resultString = "Token time out ";
            result = false;
        } catch (AlgorithmMismatchException e) {
            //  算法不匹配异常
            resultString = "Token AlgorithmMismatchException ";
            result = false;
        } catch (Exception e) {
            //  token无效
            resultString = "Token invalid ";
            result = false;
        }

        if (tokenResult == null) {
            resultString = "Token invalid ";
            result = false;
        } else {
            //  拼接 key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();

            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity);
            //  从 Reids 中获取 token
            String tokenRedis = stringRedisTemplate.opsForValue().get(tokenKey);
            if (StringUtils.isBlank(tokenRedis)) {
                resultString = "Token invalid ";
                result = false;
            }else {
                //  判断 token 是否一致
                if (!token.trim().equals(tokenRedis.trim())){
                    resultString = "Token invalid ";
                    result = false;
                }
            }
        }


        //  判断 token 是否一致

        if (!result) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json;charset=UTF-8");
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
        }


        return result;
    }
}
