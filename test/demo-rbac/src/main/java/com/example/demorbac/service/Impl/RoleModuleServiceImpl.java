package com.example.demorbac.service.Impl;

import com.example.demorbac.mapper.RoleModuleMapper;
import com.example.demorbac.service.RoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 11:57 2018/9/24
 * @Modified By:
 */
@Service
public class RoleModuleServiceImpl implements RoleModuleService {
    @Autowired(required = false)
    private RoleModuleMapper roleModuleMapper;
}
