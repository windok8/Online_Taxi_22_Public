package com.wdk.serviceprice.controller;

import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.request.ForecastPriceDTO;
import com.wdk.serviceprice.service.ForecastPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@RestController
@Slf4j
public class ForecastPriceController {


    @Autowired
    private ForecastPriceService forecastPriceService;

    /**
     * 计算预估价格
     * @param forecastPriceDTO
     * @return
     */
    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO){

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();


        return forecastPriceService.forecastPrice(depLongitude,depLatitude,destLongitude,destLatitude);
    }

    /**
     * 计算实际价格
     * @param distance
     * @param duration
     * @param cityCode
     * @param vehicleType
     * @return
     */
//    @PostMapping("/calculate-price")
//    public ResponseResult<Double> calculatePrice(@RequestParam Integer distance , @RequestParam Integer duration, @RequestParam String cityCode, @RequestParam String vehicleType){
//        return forecastPriceService.calculatePrice(distance,duration,cityCode,vehicleType);
//    }

}
