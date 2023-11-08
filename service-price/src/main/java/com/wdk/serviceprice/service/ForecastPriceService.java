package com.wdk.serviceprice.service;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.request.ForecastPriceDTO;
import com.wdk.internalcommon.response.DirectionReponse;
import com.wdk.internalcommon.response.ForecastPriceResponse;
import com.wdk.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@Service
@Slf4j
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;


    /**
     * @param depLatitude
     * @param depLongitude
     * @param destLatitude
     * @param destLongitude
     * @return ResponseResult
     * @Author: Windok
     * @Description: 根据 出发地和目的地的经纬度 获取预估价格
     **/
    public ResponseResult forecastPrice(String depLatitude, String depLongitude, String destLatitude, String destLongitude) {

        log.info("出发地经度: " + depLatitude);
        log.info("出发地纬度: " + depLongitude);
        log.info("目的地经度: " + destLatitude);
        log.info("目的地纬度: " + destLongitude);

        log.info("调用地图服务，查询距离和时长");
        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        ResponseResult<DirectionReponse> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        log.info("距离: " + distance);
        log.info("时长: " + duration);

        log.info("读取计价规则");
        log.info("根据距离、时长和计价规则，计算价格");


        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(13.14);

        return ResponseResult.success();
    }

}
