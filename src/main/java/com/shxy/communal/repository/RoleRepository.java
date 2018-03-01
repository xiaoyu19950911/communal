package com.shxy.communal.repository;

import com.shxy.communal.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xiaoyu
 * @Descripstion:
 * @Date:Created in 2018/2/11 14:39
 * @Modified By:
 */
public interface RoleRepository extends JpaRepository<Role,Integer>{
    Role findById(Integer roleId);
}
