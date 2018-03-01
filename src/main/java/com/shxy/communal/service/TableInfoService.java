package com.shxy.communal.service;

import com.shxy.communal.domain.TableInfo;
import com.shxy.communal.request.QueryColumnsRequest;
import com.shxy.communal.request.UpdateIntegrityRequest;
import com.shxy.communal.response.*;
import com.shxy.communal.utils.Result;
import org.springframework.validation.BindingResult;

import javax.management.Query;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 13:53
 * @Modified By:
 */
public interface TableInfoService {

    Result<QueryTableInfoResponse> getTableInfo(TableInfo request, BindingResult bindingResult);

    Result<QueryColumnsResponse> getColumns(QueryColumnsRequest request, BindingResult bindingResult);

    Result<QueryTabledefResponse> getTabledef(QueryColumnsRequest request, BindingResult bindingResult);

    Result<QueryPronameResponse> getProname(String bizId,Integer type);

    Result<QueryInfoListResponse> getInfoList(String tableName,String nodeId);

    Result modifyIntegrity(UpdateIntegrityRequest request);
}
