package com.wdk.servicemap.service;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.response.DirectionReponse;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@Service
public class DirectionService {

    /**
     * @Author: Windok
     * @Description:    根据起点经纬度和终点经纬度获取距离（米）和时长（分钟）
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return ResponseResult
     **/
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        DirectionReponse directionReponse = new DirectionReponse();
        directionReponse.setDistance(1000);
        directionReponse.setDuration(10);

        return ResponseResult.success(directionReponse);
    }

}
