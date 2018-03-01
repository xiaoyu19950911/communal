package com.shxy.communal.repository;

import com.shxy.communal.domain.BusinessColumnsInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 17:50
 * @Modified By:
 */
public interface BusinessColumnsInfoRepository extends JpaRepository<BusinessColumnsInfo,Integer>{

    BusinessColumnsInfo findByViewCol(String viewCol);
}
