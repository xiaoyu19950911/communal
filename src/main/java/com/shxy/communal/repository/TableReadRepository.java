package com.shxy.communal.repository;

import com.shxy.communal.domain.TableRead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 17:31
 * @Modified By:
 */
public interface TableReadRepository extends JpaRepository<TableRead,Integer>{
    List<TableRead> findAllByTableNameAndNodeId(String tableName,String nodeId);
}
