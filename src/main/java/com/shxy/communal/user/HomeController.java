package com.shxy.communal.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/9 11:09
 * @Modified By:
 */
@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }
}
