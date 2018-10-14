package com.example.demorbac.service;

import com.example.demorbac.entity.Result;
import com.example.demorbac.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 9:35 2018/9/24
 * @Modified By:
 */
public interface RoleService {
    List<Role> selectAll(int pageNum);
    List<String> selectDynamic(Role role,int pageNum);
    Result addRole(Role role,Map map);
    Result updateRole(Role role,Map map);
    Result deleteRole(long id,Map map);
}
