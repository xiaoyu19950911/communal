package com.shxy.communal.exception;

import com.shxy.communal.enums.ResultEnums;
import com.shxy.communal.utils.Result;
import com.shxy.communal.utils.ResultUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 10:29
 * @Modified By:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @desctiption: 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(CommunalException.class)
    @ResponseBody
    public Result handleCommunalException(CommunalException e) {
        LOGGER.error(e.getMessage(), e);
        return ResultUtils.error(ResultEnums.EXCEPTION_ERROR.getCode(), ResultEnums.EXCEPTION_ERROR.getMsg());
    }

    /**
     * @desctiption: 处理所有未知异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e){
        LOGGER.error(e.getMessage(),e);
        return ResultUtils.error(ResultEnums.UNKNOW_ERROR.getCode(),ResultEnums.UNKNOW_ERROR.getMsg());
    }

    /**
     * @desctiption: 处理权限不足
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result securityException(Exception e){
        LOGGER.error(e.getMessage(),e);
        return ResultUtils.error(ResultEnums.NO_PERMISSION.getCode(),ResultEnums.NO_PERMISSION.getMsg());
    }
}