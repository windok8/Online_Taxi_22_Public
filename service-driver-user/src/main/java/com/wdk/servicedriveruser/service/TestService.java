package com.wdk.servicedriveruser.service;


import com.wdk.internalcommon.dto.DriverCarBindingRelationship;
import com.wdk.internalcommon.dto.ResponseResult;
import com.wdk.servicedriveruser.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Windok
 * @date: 2023-11-11
 * @Description:
 * @version: 1.0
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public ResponseResult testGetCarUser(){
        DriverCarBindingRelationship driverCarBindingRelationship = testMapper.selectById("1584360410912718849");
        return ResponseResult.success(driverCarBindingRelationship);
    }

}
