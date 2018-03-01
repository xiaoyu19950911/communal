package com.shxy.communal.repository;

import com.shxy.communal.domain.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 14:10
 * @Modified By:
 */
public interface TableInfoRepository extends JpaRepository<TableInfo, Integer> {

    List<TableInfo> findAllByBussinessId(String bizId);

    List<TableInfo> findByBussinessIdAndTableType(String bizId,Integer type);

}
