package com.shxy.communal.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 17:26
 * @Modified By:
 */
@Data
public class QueryInfoListResponse {

    private Map<String,List<String>> result;
}
