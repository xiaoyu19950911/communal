package com.shxy.communal.request;

import com.shxy.communal.request.requestV0.Scope;
import lombok.Data;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 15:46
 * @Modified By:
 */
@Data
public class QueryColumnsRequest {
    private String tableName;//表名

    private Scope scope;//范围
}
