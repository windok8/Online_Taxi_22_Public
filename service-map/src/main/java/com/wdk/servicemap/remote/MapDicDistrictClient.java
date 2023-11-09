package com.wdk.servicemap.remote;

import com.wdk.internalcommon.constant.AmapConfigConstants;
import com.wdk.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Windok
 * @date: 2023-11-09
 * @Description:
 * @version: 1.0
 */
@Service
public class MapDicDistrictClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public String dicDistrict(String keywords) {

        //  https://restapi.amap.com/v3/config/district?
        //  keywords=北京&subdistrict=2&key=<用户的key>

        //  拼装 请求的url

        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?keywords=" + keywords);
        url.append("&subdistrict=3");
        url.append("&key=" + amapKey);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);

        return forEntity.getBody();
    }

}
