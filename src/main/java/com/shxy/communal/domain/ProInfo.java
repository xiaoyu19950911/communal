package com.shxy.communal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 16:49
 * @Modified By:
 */
@Data
@Entity
public class ProInfo {
    @Id
    @GeneratedValue
    private Integer id;

    private String bizId;

    private Integer type;

    private String proName;
}
