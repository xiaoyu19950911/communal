package com.shxy.communal.response;

import lombok.Data;

import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 15:22
 * @Modified By:
 */
@Data
public class QueryTabledefResponse {
    private List<String> columnlist;
}
