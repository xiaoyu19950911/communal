package com.shxy.communal.request;

import com.shxy.communal.request.requestV0.ReadDataReqeustV0;
import lombok.Data;

import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 14:44
 * @Modified By:
 */
@Data
public class ReadDataReqeust {

    private String buzId;

    private String nodeId;

    private List<ReadDataReqeustV0> idparams;

    private String tableName;

    private Integer mainId;
}
