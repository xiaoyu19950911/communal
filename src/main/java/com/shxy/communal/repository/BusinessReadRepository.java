package com.shxy.communal.repository;

import com.shxy.communal.domain.BusinessRead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 17:11
 * @Modified By:
 */
public interface BusinessReadRepository extends JpaRepository<BusinessRead,Integer>{
    List<BusinessRead> findAllByNodeIdAndBelongTable(String nodeId,String tableName);
}
