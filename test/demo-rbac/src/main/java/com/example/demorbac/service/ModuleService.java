package com.example.demorbac.service;

import com.example.demorbac.entity.Module;
import com.example.demorbac.entity.Result;
import com.example.demorbac.vo.ModuleDynamic;
import com.example.demorbac.vo.ModuleJoin;

import java.util.List;
import java.util.Map;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 10:55 2018/9/24
 * @Modified By:
 */
public interface ModuleService {
    List<Module> selectAll(int pageNum);
    List<Module> selectDynamic(ModuleDynamic moduleDynamic,int pageNum);
    Result addModule(Module module,Map map);
    Result updateModule(Module module,Map map);
    Result deleteModule(long id);
    List<ModuleJoin> selectModuleList(long managerId);
}
