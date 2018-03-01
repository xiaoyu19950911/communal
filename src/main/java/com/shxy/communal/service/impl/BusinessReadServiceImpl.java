package com.shxy.communal.service.impl;

import com.google.common.collect.Lists;
import com.shxy.communal.domain.BusinessRead;
import com.shxy.communal.domain.ColumnsInfo;
import com.shxy.communal.domain.TableInfo;
import com.shxy.communal.repository.*;
import com.shxy.communal.request.ReadDataReqeust;
import com.shxy.communal.request.UpdateIntegrityRequest;
import com.shxy.communal.service.BusinessReadService;
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

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 14:36
 * @Modified By:
 */
@Service
public class BusinessReadServiceImpl implements BusinessReadService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BusinessReadServiceImpl.class);

    @Autowired
    TableInfoRepository tableInfoRepository;

    @Autowired
    TableReadRepository tableReadRepository;

    @Autowired
    BusinessReadRepository businessReadRepository;

    @Autowired
    BusinessColumnsInfoRepository businessColumnsInfoRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ColumnsInfoRepository columnsInfoRepository;


    /*@Override
    @Transactional
    public Result queryData(ReadDataReqeust reqeust) {
        Map<String, Object> map = new HashMap<>();
        List<TableInfo> tableInfoList = tableInfoRepository.findByBussinessIdAndTableType(reqeust.getBuzId(), 1);
        tableInfoList.stream().forEach(tableInfo -> {
            reqeust.getIdparams().stream().forEach(id -> {
                map.put("P01001", id.getPersonId());
                map.put("companyid", id.getUnitId());
            });
            try {
                Class clazz = Class.forName("com.shxy.communal.domain." + tableInfo.getTableName());
                Object object = clazz.newInstance();
                BeanUtils.populate(object, map);
                entityManager.merge(object);
                queryzbData(reqeust);
                queryfbData(reqeust);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });


        return ResultUtils.success();
    }*/

/*    @Override
    @Transactional
    public Result queryzbData(ReadDataReqeust reqeust){
        List<TableInfo> tableInfoList = tableInfoRepository.findByBussinessIdAndTableType(reqeust.getBuzId(), 1);
        Map<String, Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        tableInfoList.stream().forEach(tableInfo -> {
            List<BusinessRead> businessReadList = businessReadRepository.findAllByNodeIdAndBelongTable(reqeust.getNodeId(), tableInfo.getTableName());
            businessReadList.stream().forEach(businessRead -> {
                map.put(businessRead.getGoalClumns(), businessRead.getSourceColumns());//要包括主键
            });
            try {
                Class clazz = Class.forName("com.shxy.communal.domain." + tableInfo.getTableName());
                Object obj = clazz.newInstance();
                BeanUtils.populate(obj, map);
                entityManager.merge(obj);
                list.add(tableInfo.getId());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return ResultUtils.success(list);
    }*/

    /*@Override
    @Transactional
    public Result queryzbData(ReadDataReqeust reqeust) {
        List<TableInfo> tableInfoList = tableInfoRepository.findByBussinessIdAndTableType(reqeust.getBuzId(), 1);
        Map<String, Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        tableInfoList.stream().forEach(tableInfo -> {
            List<BusinessRead> businessReadList = businessReadRepository.findAllByNodeIdAndBelongTable(reqeust.getNodeId(), tableInfo.getTableName());
            businessReadList.stream().forEach(businessRead -> {
                map.put(businessRead.getGoalClumns(), businessRead.getSourceColumns());//要包括主键
            });
            try {
                String sql = "update " + tableInfo.getTableName() + " SET " + dTableCols + " VALUES " + tempTableSql + " WHERE id=";
                LOGGER.info(sql);
                jdbcTemplate.execute(sql);
                Class clazz = Class.forName("com.shxy.communal.domain." + tableInfo.getTableName());
                Object obj = clazz.newInstance();
                BeanUtils.populate(obj, map);
                entityManager.merge(obj);
                list.add(tableInfo.getId());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return ResultUtils.success(list);
    }*/

    @Override
    @Transactional
    public Result queryzbData(ReadDataReqeust reqeust) {
        List<Integer> list = new ArrayList<>();
        reqeust.getIdparams().stream().forEach(id -> {
            list.add(readZbData(id.getPersonId(), id.getUnitId(), reqeust.getBuzId(), reqeust.getNodeId(), reqeust.getTableName(), reqeust.getMainId()));
        });
        return ResultUtils.success(list);
    }

    @Override
    @Transactional
    public Result queryfbData(ReadDataReqeust reqeust) {
        List<Integer> list = new ArrayList<>();
        reqeust.getIdparams().stream().forEach(id -> {
            list.add(readFbData(id.getPersonId(), id.getUnitId(), reqeust.getBuzId(), reqeust.getNodeId(), reqeust.getTableName(), reqeust.getMainId()));
        });
        return ResultUtils.success(list);
    }

    @Override
    @Transactional
    public Result queryData(ReadDataReqeust reqeust) {
        List<TableInfo> tableInfoList = tableInfoRepository.findByBussinessIdAndTableType(reqeust.getBuzId(), 1);
        List<Integer> idList = new ArrayList<>();
        reqeust.getIdparams().stream().forEach(id -> {
            String sql = "INSERT INTO ?(P01001,companyid) VALUES (?,?)";
            LOGGER.info(sql);
            int mainId = jdbcTemplate.update(sql,tableInfoList.get(0).getTableName(),id.getPersonId(),id.getUnitId());
            idList.add(mainId);
            readZbData(id.getPersonId(), id.getUnitId(), reqeust.getBuzId(), reqeust.getNodeId(), tableInfoList.get(0).getTableName(), mainId);
            readFbData(id.getPersonId(), id.getUnitId(), reqeust.getBuzId(), reqeust.getNodeId(), tableInfoList.get(0).getTableName(), mainId);
            String sql1 = "update ? set isintegrity=1 WHERE id=?";
            LOGGER.info(sql);
            jdbcTemplate.update(sql1,tableInfoList.get(0).getTableName(),tableInfoList.get(0).getId());
        });
        return ResultUtils.success(idList);
    }

    public int readZbData(String personId, String companyId, String bizId, String nodeId, String mainTable, Integer mainId) {
        List<BusinessRead> businessReadList = businessReadRepository.findAllByNodeIdAndBelongTable(nodeId, mainTable);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        businessReadList.stream().forEach(businessRead -> {
            stringBuilder.append(businessRead.getSourceColumns());
            stringBuilder.append(",");
            stringBuilder1.append(businessRead.getGoalClumns());
            stringBuilder1.append(",");
        });
        String tempSelectSql = stringBuilder.substring(0, stringBuilder.length() - 1);
        String tTableCols = tempSelectSql;
        ColumnsInfo columnsInfo = columnsInfoRepository.findByColCode(tTableCols);
        String tempCol;
        if (columnsInfo.getColType() == 3) {
            tempCol = "str_to_date(" + columnsInfo.getColCode() + ",'%Y-%m-%d')";
        } else if (columnsInfo.getColType() == 4) {
            tempCol = "str_to_date(" + columnsInfo.getColCode() + ",'%Y-%m')";
        } else if (columnsInfo.getColType() == 5) {
            tempCol = "str_to_date(" + columnsInfo.getColCode() + ",'%Y')";
        } else {
            tempCol = columnsInfo.getColCode();
        }
        String tempTableSql = tempCol;
        String dTableCols = stringBuilder1.substring(0, stringBuilder1.length() - 1);
        String sql = "update ? SET (?) VALUES (?) WHERE id=?";
        LOGGER.info(sql);
        return jdbcTemplate.update(sql,mainTable,dTableCols,tempTableSql,mainId);
    }

    public int readFbData(String id, String personId, String companyId, String nodeId, String mainTable, int mainId) {
        List<BusinessRead> businessReadList = businessReadRepository.findAllByNodeIdAndBelongTable(nodeId+"_01", mainTable);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder1 = new StringBuilder();
        businessReadList.stream().forEach(businessRead -> {
            stringBuilder.append(businessRead.getSourceColumns());
            stringBuilder.append(",");
            stringBuilder1.append(businessRead.getGoalClumns());
            stringBuilder1.append(",");
        });
        String tempSelectSql = stringBuilder.substring(0, stringBuilder.length() - 1);
        String tTableCols = tempSelectSql;
        ColumnsInfo columnsInfo = columnsInfoRepository.findByColCode(tTableCols);
        String tempCol;
        if (columnsInfo.getColType() == 3) {
            tempCol = "str_to_date(" + columnsInfo.getColCode() + ",'%Y-%m-%d')";
        } else if (columnsInfo.getColType() == 4) {
            tempCol = "str_to_date(" + columnsInfo.getColCode() + ",'%Y-%m')";
        } else if (columnsInfo.getColType() == 5) {
            tempCol = "str_to_date(" + columnsInfo.getColCode() + ",'%Y')";
        } else {
            tempCol = columnsInfo.getColCode();
        }
        String tempTableSql = tempCol;
        String dTableCols = stringBuilder1.substring(0, stringBuilder1.length() - 1);
        String sql="INSERT INTO "+mainTable+"("+dTableCols+","+mainTable+"_ID,PKID) VALUES ("+tempTableSql+")";
        LOGGER.info(sql);
        return jdbcTemplate.update(sql);
    }

}
