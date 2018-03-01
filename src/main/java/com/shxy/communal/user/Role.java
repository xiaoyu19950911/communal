package com.shxy.communal.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/8 15:43
 * @Modified By:
 */
@Data
@Entity
public class Role {


    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "role_name")
    private String name;
}
