package com.shxy.communal.repository;

import com.shxy.communal.domain.ColumnsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 16:08
 * @Modified By:
 */
public interface ColumnsInfoRepository extends JpaRepository<ColumnsInfo, Integer> {
    List<ColumnsInfo> findAllByColTableName(String colTableName);

    @Query(value = "select column_name from information_schema.columns where table_schema ='shxy' and table_name =?1",nativeQuery = true)
    List<String> findcolumns(String tableName);

    ColumnsInfo findByColCode(String cdoe);

}
