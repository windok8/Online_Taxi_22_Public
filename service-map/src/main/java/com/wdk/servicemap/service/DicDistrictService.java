package com.wdk.servicemap.service;

import com.wdk.internalcommon.constant.AmapConfigConstants;
import com.wdk.internalcommon.constant.CommonStatuseEnum;
import com.wdk.internalcommon.dto.DicDistrict;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.servicemap.mapper.DicDistrictMapper;
import com.wdk.servicemap.remote.MapDicDistrictClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public ResponseResult initDicDistrict(String keywords) {

        //  请求地图
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        //  解析地图返回的数据
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AmapConfigConstants.STATUS);
        if (status != 1) {
            return ResponseResult.fail(CommonStatuseEnum.MAP_DISTRICT_ERROR.getCode(), CommonStatuseEnum.MAP_DISTRICT_ERROR.getValue());
        }
        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
        for (int country = 0; country < countryJsonArray.size(); country++) {
            JSONObject countryJsonObject = countryJsonArray.getJSONObject(country);
            String countryAddressCode = countryJsonObject.getString(AmapConfigConstants.ADCODE);
            String countryAddressName = countryJsonObject.getString(AmapConfigConstants.NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryJsonObject.getString(AmapConfigConstants.LEVEL);

            insertDicDistrict(countryAddressCode, countryAddressName, countryLevel, countryParentAddressCode);
            JSONArray proviceJsonArray = countryJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
            for (int p = 0; p < proviceJsonArray.size(); p++) {
                JSONObject proviceJsonObject = proviceJsonArray.getJSONObject(p);
                String proviceAddressCode = proviceJsonObject.getString(AmapConfigConstants.ADCODE);
                String proviceAddressName = proviceJsonObject.getString(AmapConfigConstants.NAME);
                String proviceParentAddressCode = countryAddressCode;
                String proviceLevel = proviceJsonObject.getString(AmapConfigConstants.LEVEL);

                insertDicDistrict(proviceAddressCode, proviceAddressName, proviceLevel, proviceParentAddressCode);

                JSONArray cityArray = proviceJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                for (int city = 0; city < cityArray.size(); city++) {
                    JSONObject cityJsonObject = cityArray.getJSONObject(city);
                    String cityAddressCode = cityJsonObject.getString(AmapConfigConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AmapConfigConstants.NAME);
                    String cityParentAddressCode = proviceAddressCode;
                    String cityLevel = cityJsonObject.getString(AmapConfigConstants.LEVEL);

                    insertDicDistrict(cityAddressCode, cityAddressName, cityLevel, cityParentAddressCode);

                    JSONArray districtArray = cityJsonObject.getJSONArray(AmapConfigConstants.DISTRICTS);
                    for (int d = 0; d < districtArray.size(); d++) {
                        JSONObject districtJsonObject = districtArray.getJSONObject(d);
                        String districtAddressCode = districtJsonObject.getString(AmapConfigConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AmapConfigConstants.NAME);
                        String districtParentAddressCode = cityAddressCode;
                        String districtLevel = districtJsonObject.getString(AmapConfigConstants.LEVEL);

                        if (districtLevel.equals(AmapConfigConstants.STREET)) {
                            continue;
                        }

                        insertDicDistrict(districtAddressCode, districtAddressName, districtLevel, districtParentAddressCode);

                    }
                }
            }

        }

        return ResponseResult.success("");
    }

    public void insertDicDistrict(String addressCode, String addressName, String parentAddressCode, String level) {
        DicDistrict dicDistrict = new DicDistrict();
        dicDistrict.setAddressCode(addressCode);
        dicDistrict.setAddressName(addressName);
        dicDistrict.setParentAddressCode(parentAddressCode);
        dicDistrict.setLevel(generateLevel(level));
        dicDistrictMapper.insert(dicDistrict);
    }

    public int generateLevel(String level) {
        int levelInt = 0;
        if (level.trim().equals("country")) levelInt = 0;
        if (level.trim().equals("province")) levelInt = 1;
        if (level.trim().equals("city")) levelInt = 2;
        if (level.trim().equals("district")) levelInt = 3;
        return levelInt;
    }


}
