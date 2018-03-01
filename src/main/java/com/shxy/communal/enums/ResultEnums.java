package com.shxy.communal.enums;

import lombok.Data;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 14:58
 * @Modified By:
 */
public enum  ResultEnums {

    UNKNOW_ERROR(-1,"未知错误!"),
    SUCCESS(101,"成功!"),
    PARAMETERS_ERROR(104,"参数错误!"),
    EXCEPTION_ERROR(105,"内部异常!"),
    NO_PERMISSION(201,"权限不足"),
    USERNAME_NULL(106,"用户名不能为空！")
    ;

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

