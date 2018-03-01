package com.shxy.communal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 17:23
 * @Modified By:
 */
@Data
@Entity
public class TableRead {

    @Id
    @GeneratedValue
    private Integer id;

    private String sourceColumn;

    private String nodeId;

    private String sourceTableName;

    private String tableName;
}
