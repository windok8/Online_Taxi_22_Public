package com.wdk.servicemap.service;

import com.wdk.internalcommon.constant.AmapConfigConstants;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.servicemap.remote.MapDicDistrictClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    public ResponseResult initDicDistrict(String keywords) {

        //  请求地图
        String dicDistrict = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrict);


        return ResponseResult.success("");
    }



}
