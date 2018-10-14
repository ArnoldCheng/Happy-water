package com.example.demorbac.mapper;

import com.example.demorbac.entity.Module;
import com.example.demorbac.vo.ModuleDynamic;
import com.example.demorbac.vo.ModuleJoin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 10:49 2018/9/24
 * @Modified By:
 */
public interface ModuleMapper {
    List<Module> selectAll();
    List<String> selectModuleByAccount(String moduleUrl);
    List<Module> selectDynamic(ModuleDynamic moduleDynamic);
    void addModule(Module module);
    Integer selectModuleLevelById(long id);
    int selectModuleRepeat(String moduleName);
    void upModule(Module module);
    void deleteModule(long id);
    String selectModuleNameById(long id);
    List<ModuleJoin> selectTopModuleById(long id,long fatherModuleId);
    int selectModuleExistByModuleId(long id);
    Module selectFatherModuleByModuleName(String moduleName);
    Module selectModuleNameAndLevelById(long id);
    List<Long> selectSonModuleByModuleId(long id);
    List<String> selectModulesByModuleId(@Param("sonIds")List<Long> sonIds);
    void deleteModules(@Param("sonIds")List<Long> id);
}
