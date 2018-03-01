package com.shxy.communal.service;

import com.shxy.communal.request.ReadDataReqeust;
import com.shxy.communal.utils.Result;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 14:36
 * @Modified By:
 */
public interface BusinessReadService {

    Result queryData(ReadDataReqeust reqeust);

    Result queryzbData(ReadDataReqeust reqeust) throws ParseException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException;

    Result queryfbData(ReadDataReqeust reqeust);
}
