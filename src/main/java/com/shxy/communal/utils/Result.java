package com.shxy.communal.utils;

import lombok.Data;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 13:48
 * @Modified By:
 */
@Data
public class Result<T> {
    private Integer code;//返回码

    private String msg;//返回信息

    private T result;//返回具体内容
}
