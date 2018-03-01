package com.shxy.communal.request.requestV0;

import lombok.Data;

import java.util.List;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/1 15:47
 * @Modified By:
 */
@Data
public class Scope {
    private List<String> incol;//范围（in）

    private List<String> excol;//范围（not in）
}
