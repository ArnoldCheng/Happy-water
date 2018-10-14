package com.example.demorbac.service;

import com.example.demorbac.entity.Manager;
import com.example.demorbac.entity.Result;
import com.example.demorbac.vo.ManagerDynamic;

import java.util.List;
import java.util.Map;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 21:59 2018/9/23
 * @Modified By:
 */
public interface ManagerService {
    List<Manager> selectAll(int pageNum);
    List<Manager> selectDynamic(ManagerDynamic managerDynamic, int pageNum);
    void updatePwdById(Manager manager);
    Result addManager(Manager manager,Map map);
    Result login(Manager manager);
    Result updateManager(Manager manager,Map map);
    Result deleteManager(long id,Map map);
    List<String> selectRoleName();
    Manager selectManagerByAccount(String account);
    Manager selectPasswordAndSaltByManagerId(Long managerId);
}
