package com.shxy.communal.repository;

import com.shxy.communal.domain.ProInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 16:50
 * @Modified By:
 */
public interface ProInfoRepository extends JpaRepository<ProInfo, Integer> {
    ProInfo findByBizIdAndType(String bizId,Integer type);
}
