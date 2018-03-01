package com.shxy.communal.controller;

import com.shxy.communal.repository.UserRepository;
import com.shxy.communal.user.User;
import com.shxy.communal.utils.Result;
import com.shxy.communal.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/11 16:29
 * @Modified By:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/adduser")
    public Result creatUser(@RequestBody User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword().trim()));
        userRepository.save(user);
        return ResultUtils.success();
    }
}
