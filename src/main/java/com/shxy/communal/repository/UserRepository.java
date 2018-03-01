package com.shxy.communal.repository;

import com.shxy.communal.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/9 10:12
 * @Modified By:
 */
public interface UserRepository extends JpaRepository<User,Integer>{

    User findByUsername(String username);
}
