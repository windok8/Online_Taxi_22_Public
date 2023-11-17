package com.wdk.servicedriveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wdk.internalcommon.constant.CommonStatuseEnum;
import com.wdk.internalcommon.constant.DriverCarConstants;
import com.wdk.internalcommon.dto.DriverCarBindingRelationship;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.Oneway;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author : Windok
 * @date: 2023-11-11
 * @Description:
 * @version: 1.0
 */
@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        // 判断：如果参数中的车辆和司机，已经存在绑定，则不允许再次绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.BIND_STATE_BINDED);
        Long result = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (result.intValue() > 0) {
            return ResponseResult.fail(CommonStatuseEnum.DRIVER_CAR_BINO_EXISTS.getCode(), CommonStatuseEnum.DRIVER_CAR_BINO_EXISTS.getValue());
        }

        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("bind_state", DriverCarConstants.BIND_STATE_BINDED);
        result = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (result.intValue() > 0) {
            return ResponseResult.fail(CommonStatuseEnum.DRIVER_BIND_EXISTS.getCode(), CommonStatuseEnum.DRIVER_BIND_EXISTS.getValue());
        }

        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.BIND_STATE_BINDED);
        result = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (result.intValue() > 0) {
            return ResponseResult.fail(CommonStatuseEnum.CAR_BIND_EXISTS.getCode(), CommonStatuseEnum.CAR_BIND_EXISTS.getValue());
        }
        LocalDateTime now = LocalDateTime.now();
        driverCarBindingRelationship.setBindingTime(now);
        driverCarBindingRelationship.setBindState(DriverCarConstants.BIND_STATE_BINDED);
        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResponseResult.success("");
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> map = new HashMap<>();
        map.put("driver_id", driverCarBindingRelationship.getDriverId());
        map.put("car_id", driverCarBindingRelationship.getCarId());
        map.put("bind_state", DriverCarConstants.BIND_STATE_BINDED);
        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        if (driverCarBindingRelationships.isEmpty()) {
            return ResponseResult.fail(CommonStatuseEnum.DRIVER_CAR_BINO_NOT_EXISTS.getCode(), CommonStatuseEnum.DRIVER_CAR_BINO_NOT_EXISTS.getValue());
        }
        DriverCarBindingRelationship bindingRelationship = driverCarBindingRelationships.get(0);
        bindingRelationship.setBindState(DriverCarConstants.BIND_STATE_UNBIND);
        bindingRelationship.setUnBindingTime(now);
        driverCarBindingRelationshipMapper.updateById(bindingRelationship);
        return ResponseResult.success("");
    }

}
