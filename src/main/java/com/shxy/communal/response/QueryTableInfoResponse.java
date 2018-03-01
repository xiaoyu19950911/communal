package com.shxy.communal.response;

import lombok.Data;

import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 13:50
 * @Modified By:
 */
@Data
public class QueryTableInfoResponse {
    private List<String> mainTable;

    private List<String> sideTable;
}
