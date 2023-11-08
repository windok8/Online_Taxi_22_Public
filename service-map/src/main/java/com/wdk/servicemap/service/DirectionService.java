package com.wdk.servicemap.service;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.response.DirectionReponse;
import com.wdk.servicemap.remote.MapDirectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@Service
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

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
        //  调用高德地图API获取距离和时长
        DirectionReponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);

        return ResponseResult.success(direction);
    }

}
