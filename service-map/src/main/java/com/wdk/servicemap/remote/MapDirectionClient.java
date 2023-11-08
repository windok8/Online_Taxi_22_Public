package com.wdk.servicemap.remote;

import com.wdk.internalcommon.constant.AmapConfigConstants;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.internalcommon.response.DirectionReponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Windok
 * @date: 2023-11-07
 * @Description:
 * @version: 1.0
 */
@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;


    public DirectionReponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        //  调用高德地图API获取距离和时长
        //  1. 组装请求参数url
        //
        /**
         * https://restapi.amap.com/v3/direction/driving?
         * origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=json&key=
         **/
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(AmapConfigConstants.DIRECTION_URL);
        urlBuild.append("?origin=").append(depLongitude).append(",").append(depLatitude);
        urlBuild.append("&destination=").append(destLongitude).append(",").append(destLatitude);
        // urlBuild.append("&extensions=all&output=json&key=").append(AmapConfigConstants.DIRECTION_KEY);
        urlBuild.append("&extensions=base&output=json&key=" + amapKey);
        log.info("urlBuild:{}", urlBuild.toString());

        //  2. 调用接口
        ResponseEntity<String> driectionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        String directionString = driectionEntity.getBody();
        log.info("高德地图，路径规划，返回信息:{}", driectionEntity.getBody());
        //  3. 解析返回结果
        DirectionReponse directionReponse = parseDriectionEntity(directionString);
        return directionReponse;
    }

    private DirectionReponse parseDriectionEntity(String directionString) {
        DirectionReponse directionReponse = null;
        try {

            //  最外层
            JSONObject result = JSONObject.fromObject(directionString);
            if (result.has(AmapConfigConstants.STATUS)) {
                int status = result.getInt(AmapConfigConstants.STATUS);
                if (status == 1) {
                    if (result.has(AmapConfigConstants.ROUTE)) {
                        JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathsArray.getJSONObject(0);
                        directionReponse = new DirectionReponse();
                        if (pathObject.has(AmapConfigConstants.DISTANCE) && pathObject.has(AmapConfigConstants.DURATION)) {
                            int distance = pathObject.getInt(AmapConfigConstants.DISTANCE);
                            int duration = pathObject.getInt(AmapConfigConstants.DURATION);
                            directionReponse.setDistance(distance);
                            directionReponse.setDuration(duration);
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
        return directionReponse;
    }

}
