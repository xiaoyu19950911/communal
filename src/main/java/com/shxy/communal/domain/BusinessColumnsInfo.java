package com.shxy.communal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 17:46
 * @Modified By:
 */
@Data
@Entity
public class BusinessColumnsInfo {
    @Id
    @GeneratedValue
    private Integer id;

    private String viewTable;

    private String viewCol;

    private Integer viewType;
}
