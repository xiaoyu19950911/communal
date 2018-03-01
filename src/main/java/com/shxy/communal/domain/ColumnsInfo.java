package com.shxy.communal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 16:03
 * @Modified By:
 */
@Entity
@Data
public class ColumnsInfo {

    @Id
    @GeneratedValue
    private Integer id;

    private String colTableName;

    private String colCode;

    private String colName;

    private Integer colType;
}
