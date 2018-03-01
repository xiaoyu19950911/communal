package com.shxy.communal.response;

import com.shxy.communal.response.responseV0.QueryColumnsResponseV0;
import lombok.Data;

import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 15:51
 * @Modified By:
 */
@Data
public class QueryColumnsResponse {
    private List<QueryColumnsResponseV0> queryColumnsResponseV0List;
}
