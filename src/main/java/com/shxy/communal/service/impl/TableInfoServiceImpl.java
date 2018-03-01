package com.shxy.communal.service.impl;

import com.shxy.communal.domain.ColumnsInfo;
import com.shxy.communal.domain.ProInfo;
import com.shxy.communal.domain.TableInfo;
import com.shxy.communal.domain.TableRead;
import com.shxy.communal.enums.ResultEnums;
import com.shxy.communal.repository.ColumnsInfoRepository;
import com.shxy.communal.repository.ProInfoRepository;
import com.shxy.communal.repository.TableInfoRepository;
import com.shxy.communal.repository.TableReadRepository;
import com.shxy.communal.request.QueryColumnsRequest;
import com.shxy.communal.request.UpdateIntegrityRequest;
import com.shxy.communal.response.*;
import com.shxy.communal.response.responseV0.QueryColumnsResponseV0;
import com.shxy.communal.service.TableInfoService;
import com.shxy.communal.utils.Result;
import com.shxy.communal.utils.ResultUtils;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 13:54
 * @Modified By:
 */
@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Autowired
    TableInfoRepository tableInfoRepository;

    @Autowired
    ColumnsInfoRepository columnsInfoRepository;

    @Autowired
    ProInfoRepository proInfoRepository;

    @Autowired
    TableReadRepository tableReadRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Result<QueryTableInfoResponse> getTableInfo(TableInfo request, BindingResult bindingResult) {
        Result result;
        List<TableInfo> tableInfoList = tableInfoRepository.findAllByBussinessId(request.getBussinessId());
        if (tableInfoList.isEmpty()) {
            result = ResultUtils.success();
        } else {
            List<String> mainTableNameList = tableInfoList.stream().filter(s -> s.getTableType() == 1).map(TableInfo::getTableName).collect(Collectors.toList());
            List<String> sideTableNameList = tableInfoList.stream().filter(s -> s.getTableType() == 0).map(TableInfo::getTableName).collect(Collectors.toList());
            QueryTableInfoResponse queryTableInfoResponse = new QueryTableInfoResponse();
            queryTableInfoResponse.setMainTable(mainTableNameList);
            queryTableInfoResponse.setSideTable(sideTableNameList);
            result = ResultUtils.success(queryTableInfoResponse);
        }
        return result;
    }

    @Override
    public Result<QueryColumnsResponse> getColumns(QueryColumnsRequest request, BindingResult bindingResult) {
        Result result;
        QueryColumnsResponse queryColumnsResponse;
        List<ColumnsInfo> columnsList = columnsInfoRepository.findAllByColTableName(request.getTableName());
        if (columnsList.isEmpty()) {
            result = ResultUtils.success();
        } else {
            List<QueryColumnsResponseV0> filtercolumnsList = request.getScope().getIncol().isEmpty() ? columnsList.stream().filter(s -> !request.getScope().getExcol().contains(s.getColCode())).map(e -> new QueryColumnsResponseV0(e.getColCode(), e.getColName(), e.getColType())).collect(Collectors.toList()) : columnsList.stream().filter(s -> request.getScope().getIncol().contains(s.getColCode()) && !request.getScope().getExcol().contains(s.getColCode())).map(e -> new QueryColumnsResponseV0(e.getColCode(), e.getColName(), e.getColType())).collect(Collectors.toList());
            queryColumnsResponse = new QueryColumnsResponse();
            queryColumnsResponse.setQueryColumnsResponseV0List(filtercolumnsList);
            result = ResultUtils.success(queryColumnsResponse);
        }
        return result;
    }

    @Override
    public Result<QueryTabledefResponse> getTabledef(QueryColumnsRequest request, BindingResult bindingResult) {
        Result result;
        QueryTabledefResponse queryTabledefResponse;
        List<String> columnList = columnsInfoRepository.findcolumns(request.getTableName());
        if (columnList.isEmpty()) {
            result = ResultUtils.success();
        } else {
            System.out.println(request.getScope().getIncol().isEmpty());
            List<String> filtercolumnList = request.getScope().getIncol().isEmpty() ? columnList.stream().filter(s -> !request.getScope().getExcol().contains(s)).collect(Collectors.toList()) : columnList.stream().filter(s -> request.getScope().getIncol().contains(s) && !request.getScope().getExcol().contains(s)).collect(Collectors.toList());
            queryTabledefResponse = new QueryTabledefResponse();
            queryTabledefResponse.setColumnlist(filtercolumnList);
            result = ResultUtils.success(queryTabledefResponse);
        }
        return result;
    }

    @Override
    public Result<QueryPronameResponse> getProname(String bizId, Integer type) {
        QueryPronameResponse queryPronameResponse;
        ProInfo proInfo = proInfoRepository.findByBizIdAndType(bizId, type);
        queryPronameResponse = new QueryPronameResponse();
        queryPronameResponse.setProName(proInfo.getProName());
        return ResultUtils.success(queryPronameResponse);
    }

    @Override
    public Result<QueryInfoListResponse> getInfoList(String tableName, String nodeId) {
        QueryInfoListResponse queryInfoListResponse = new QueryInfoListResponse();
        List<TableRead> tableReadList = tableReadRepository.findAllByTableNameAndNodeId(tableName, nodeId);
        Set<String> set = new HashSet<>();
        tableReadList.stream().forEach(tableRead -> {
            set.add(tableRead.getSourceTableName());
        });
        Map<String, List<String>> map = new HashMap<>();
        for (String str : set) {
            List<String> list = tableReadList.stream().filter(s -> s.getSourceTableName().equals(str)).map(TableRead::getSourceColumn).collect(Collectors.toList());
            map.put(str, list);
        }
        queryInfoListResponse.setResult(map);
        return ResultUtils.success(queryInfoListResponse);
    }

    /*@Override
    @Transactional
    public Result modifyIntegrity(UpdateIntegrityRequest request) {
        Map<String,Object> map=new HashMap<>();
        try {
            Class clazz=Class.forName("com.shxy.communal.domain."+request.getTableName());
            Object obj=entityManager.find(clazz,request.getRecordId());
            Map<String,Object> map1= com.shxy.communal.utils.BeanMap.beanToMap(obj);
            map1.put("ISINTEGRITY",1);
            obj= com.shxy.communal.utils.BeanMap.mapToBean(map1,clazz);
            entityManager.merge(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ResultUtils.success();
    }*/

    @Override
    @Transactional
    public Result modifyIntegrity(UpdateIntegrityRequest request) {
        String sql = "update ? set isintegrity=1 WHERE id=?";
        jdbcTemplate.update(sql,request.getTableName(),request.getRecordId());
        return ResultUtils.success();
    }


}
