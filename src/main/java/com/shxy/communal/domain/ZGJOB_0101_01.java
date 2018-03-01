package com.shxy.communal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/6 10:01
 * @Modified By:
 */
@Data
@Entity
public class ZGJOB_0101_01 {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer sex;

    private String address;

    private Integer ISINTEGRITY;
}
