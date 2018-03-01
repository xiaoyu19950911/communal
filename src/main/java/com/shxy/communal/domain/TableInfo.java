package com.shxy.communal.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 13:42
 * @Modified By:
 */
@Entity
@Data
public class TableInfo {

    @Id
    @GeneratedValue
    private Integer id;//主键id

    private String tableName;//表名

    @NotBlank(message = "业务id不能为空！")
    private String bussinessId;//业务id

    private Integer tableType;//表类型（0是副表，1是主表）
}
