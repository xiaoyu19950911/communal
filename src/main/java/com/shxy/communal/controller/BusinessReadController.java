package com.shxy.communal.controller;

import com.shxy.communal.request.ReadDataReqeust;
import com.shxy.communal.response.ReadDataResponse;
import com.shxy.communal.service.BusinessReadService;
import com.shxy.communal.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 14:31
 * @Modified By:
 */
@RestController
@RequestMapping("/businessread")
public class BusinessReadController {

    @Autowired
    BusinessReadService businessReadService;

    /**
     * @return
     * @desctiption: 将人事信息表的数据转移至义务数据表（主入口）
     */
    public Result<ReadDataResponse> readData(@Valid @RequestBody ReadDataReqeust reqeust, BindingResult bindingResult) {
        return businessReadService.queryData(reqeust);
    }


    /**
     * @desctiption: 将人事信息表的数据转移至业务主数据表
     * @param reqeust
     * @param bindingResult
     * @return
     */
    public Result<ReadDataResponse> readzbData(@Valid @RequestBody ReadDataReqeust reqeust, BindingResult bindingResult) throws ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException, InvocationTargetException {
        return businessReadService.queryzbData(reqeust);
    }

    /**
     * @desctiption: 将人事信息表的数据转移至业务副数据表
     * @param reqeust
     * @param bindingResult
     * @return
     */
    public Result<ReadDataResponse> readfbData(@Valid @RequestBody ReadDataReqeust reqeust, BindingResult bindingResult) {
        return businessReadService.queryfbData(reqeust);
    }
}
