package com.wdk.internalcommon.dto;

import com.wdk.internalcommon.constant.CommonStatuseEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : Windok
 * @date: 2023-10-30
 * @Description:
 * @version: 1.0
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    /**
     * @Author: Windok
     * @Description: 成功响应的方法
     * @param data
     * @return ResponseResult
     **/
    public static <T> ResponseResult success(T data) {
        return new ResponseResult().setCode(CommonStatuseEnum.SUCCESS.getCode()).setMessage(CommonStatuseEnum.SUCCESS.getValue()).setData(data);
    }
    /**
     * @Author: Windok
     * @Description: 自定义失败：错误码和提示信息
     * @param code
     * @param message
     * @return ResponseResult
     **/
    public static ResponseResult fail(int code,String message) {
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * @Author: Windok
     * @Description: 统一的失败响应
     * @param data
     * @return ResponseResult
     **/
    public static <T> ResponseResult fail(T data) {
        return new ResponseResult().setData(data);
    }

    /**
     * @Author: Windok
     * @Description: 自定义失败：错误码和提示信息和返回数据
     * @param code
     * @param message
     * @param data
     * @return ResponseResult
     **/
    public static ResponseResult fail(int code,String message,String data) {
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }


}
