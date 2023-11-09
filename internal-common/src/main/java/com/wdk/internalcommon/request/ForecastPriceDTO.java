package com.wdk.internalcommon.request;

import lombok.Data;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@Data
public class ForecastPriceDTO {

    private String depLongitude;
    private String depLatitude;
    private String destLongitude;
    private String destLatitude;

    private String cityCode;
    private String vehicleType;

}
