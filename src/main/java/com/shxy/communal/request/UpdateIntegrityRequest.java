package com.shxy.communal.request;

import lombok.Data;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/2 14:00
 * @Modified By:
 */
@Data
public class UpdateIntegrityRequest {
    private String tableName;

    private String recordId;

    private String nodeId;
}
