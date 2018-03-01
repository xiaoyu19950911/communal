package com.shxy.communal.response.responseV0;

import lombok.Data;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 15:52
 * @Modified By:
 */
@Data
public class QueryColumnsResponseV0 {
    private String colCode;

    private String colName;

    private Integer colType;

    public QueryColumnsResponseV0(String colCode, String colName, Integer colType) {
        this.colCode = colCode;
        this.colName = colName;
        this.colType = colType;
    }

    public QueryColumnsResponseV0() {
    }
}
