package com.wdk.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.util.JwtUtils;
import net.sf.json.JSONObject;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";
        String token = request.getHeader("Authorization");
        try {
            JwtUtils.parseToken(token);
        }catch (SignatureVerificationException e){
            //  token签名错误
            resultString = "Token sign error ";
            result = false;
        }catch (TokenExpiredException e){
            //  token过期
            resultString = "Token time out ";
            result = false;
        }catch (AlgorithmMismatchException e){
            //  算法不匹配异常
            resultString = "Token AlgorithmMismatchException ";
            result = false;
        }catch (Exception e){
            //  token无效
            resultString = "Token invalid ";
            result = false;
        }

        if (!result){
            PrintWriter out = response.getWriter();
            response.setContentType("application/json;charset=UTF-8");
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
        }





        return result;
    }
}
