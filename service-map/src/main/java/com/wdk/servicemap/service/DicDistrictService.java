package com.wdk.servicemap.service;

import com.wdk.internalcommon.constant.AmapConfigConstants;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-11-09
 * @Description:
 * @version: 1.0
 */
@Service
public class DicDistrictService {

    @Value("${amap.key}")
    private String amapKey;

    public ResponseResult initDicDistrict(String keywords) {

        //  https://restapi.amap.com/v3/config/district?
        //  keywords=北京&subdistrict=2&key=<用户的key>

        //  拼装 请求的url

        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?keywords=" + keywords);
        url.append("&subdistrict=3");
        url.append("&key=" + amapKey);

        //  解析结果
        //  插入数据库

        return ResponseResult.success("");
    }

}
