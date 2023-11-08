package com.wdk.serviceprice.service;

import com.wdk.internalcommon.constant.CommonStatuseEnum;
import com.wdk.internalcommon.dto.PriceRule;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.request.ForecastPriceDTO;
import com.wdk.internalcommon.response.DirectionReponse;
import com.wdk.internalcommon.response.ForecastPriceResponse;
import com.wdk.serviceprice.mapper.PriceRuleMappper;
import com.wdk.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @Autowired
    private PriceRuleMappper priceRuleMappper;


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


        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code", "110000");
        queryMap.put("vehicle_type", "1");
        List<PriceRule> priceRules = priceRuleMappper.selectByMap(queryMap);
        if (Objects.isNull(priceRules) || priceRules.size() == 0) {
            log.info("没有找到计价规则");
            return ResponseResult.fail(CommonStatuseEnum.PRICE_RULE_EMPTY.getCode(), CommonStatuseEnum.PRICE_RULE_EMPTY.getValue());
        }

        log.info("根据距离、时长和计价规则，计算价格");


        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(getPrice(distance, duration, priceRules.get(0)));

        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * @param distance  距离
     * @param duration  时长
     * @param priceRule 计价规则
     * @return Double
     * @Author: Windok
     * @Description: 根据距离和时长计算价格
     **/
    private double getPrice(Integer distance, Integer duration, PriceRule priceRule) {
        //  BigDecimal
        BigDecimal price = new BigDecimal(0);
        //  起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareBigDecimal = new BigDecimal(startFare);
        price = price.add(startFareBigDecimal);
        //  总里程 m
        BigDecimal distanceBigDecimal = new BigDecimal(distance);
        //  总里程 km
        BigDecimal distanceMileDecimal = distanceBigDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        //  起步里程
        Integer startMile = priceRule.getStartMile();
        BigDecimal startMileBigDecimal = new BigDecimal(startMile);
        //  超出起步里程的里程
        double distanceSubtract = distanceMileDecimal.subtract(startMileBigDecimal).doubleValue();
        //  最终收费里程数
        Double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
        BigDecimal mileBigDecimal = new BigDecimal(mile);
        //  计程单价
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPricePerMileBigDecimal = new BigDecimal(unitPricePerMile);
        //  计程单价 * 最终收费里程数
        BigDecimal maileFare = mileBigDecimal.multiply(unitPricePerMileBigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(maileFare);

        //  时长费
        BigDecimal time = new BigDecimal(duration);
        //  时长分钟数
        BigDecimal timeDecimal = time.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
        //  计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPricePerMinuteBigDecimal = new BigDecimal(unitPricePerMinute);
        //  计时单价 * 时长分钟数
        BigDecimal timeFare = timeDecimal.multiply(unitPricePerMinuteBigDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        //  总价格
        price = price.add(timeFare);


        return price.doubleValue();
    }




}
