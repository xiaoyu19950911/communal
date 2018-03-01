package com.shxy.communal.controller;


import com.shxy.communal.domain.TableInfo;
import com.shxy.communal.request.QueryColumnsRequest;
import com.shxy.communal.request.UpdateIntegrityRequest;
import com.shxy.communal.response.*;
import com.shxy.communal.service.TableInfoService;
import com.shxy.communal.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: xiaoyu
 * @Descripstion: 公共类接口
 * @Date:Created in 2018/2/1 13:46
 * @Modified By:
 */
@RequestMapping("/tableinfo")
@RestController
public class TableInfoController {

    @Autowired
    TableInfoService tableInfoService;

    /**
     * @param request
     * @param bindingResult
     * @return
     * @desctiption: 查询业务表编码
     */
    @PostMapping("/gettableinfo")
    public Result<QueryTableInfoResponse> queryTableInfo(@Valid @RequestBody TableInfo request, BindingResult bindingResult) {
        return tableInfoService.getTableInfo(request, bindingResult);
    }


    /**
     * @param request
     * @param bindingResult
     * @return
     * @desctiption: 查询业务表的注册字段信息
     */
    @PostMapping("/getcolumns")
    public Result<QueryColumnsResponse> queryColumns(@Valid @RequestBody QueryColumnsRequest request, BindingResult bindingResult) {
        return tableInfoService.getColumns(request, bindingResult);
    }


    /**
     * @param request
     * @param bindingResult
     * @return
     * @desctiption: 查询业务表的表结构字段信息
     */
    @PostMapping("/gettabledef")
    public Result<QueryTabledefResponse> queryTabledef(@Valid @RequestBody QueryColumnsRequest request, BindingResult bindingResult) {
        return tableInfoService.getTabledef(request, bindingResult);
    }

    /**
     * @param bizId
     * @param type
     * @return
     * @desctiption: 查询业务读取或入库时的私有函数
     */
    @GetMapping("/getproname")
    public Result<QueryPronameResponse> queryProname(@RequestParam("bizId") String bizId, @RequestParam("type") Integer type) {
        return tableInfoService.getProname(bizId, type);
    }

    /**
     * @desctiption: 按条件修改记录完整性
     * @param request
     * @param bindingResult
     * @return
     */
    @PostMapping("/modifyintegrity")
    public Result updateIntegrity(@Valid @RequestBody UpdateIntegrityRequest request, BindingResult bindingResult) {
        return tableInfoService.modifyIntegrity(request);
    }

    /**
     * @param tableName
     * @param nodeId
     * @return
     * @desctiption: 查询业务读取涉及到的信息集及栏目的集合
     */
    @GetMapping("/getinfolist")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<QueryInfoListResponse> queryInfoList(@RequestParam("tableName") String tableName, @RequestParam("nodeId") String nodeId) {
        return tableInfoService.getInfoList(tableName, nodeId);
    }


}
