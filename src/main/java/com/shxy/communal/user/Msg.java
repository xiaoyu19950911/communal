package com.shxy.communal.user;

import lombok.Data;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/9 11:08
 * @Modified By:
 */
@Data
public class Msg {
    private String title;
    private String content;
    private String extraInfo;

    public Msg() {

    }

    public Msg(String title, String content, String extraInfo) {
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }
}
