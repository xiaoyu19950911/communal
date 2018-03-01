package com.shxy.communal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 17:09
 * @Modified By:
 */
@Data
@Entity
public class BusinessRead {
    @Id
    @GeneratedValue
    private Integer id;

    private String sourceColumns;//源字段字段

    private String nodeId;//环节id

    private String goalTable;//目标表

    private String goalClumns;//目标字段

    private String includeColumns;//包含字段

    private String belongTable;//源字段所属表

    private Integer iscompanyColumns;//是否单位字段

    private String sameColumns;//字段值与目标字段相同的业务字段

}
