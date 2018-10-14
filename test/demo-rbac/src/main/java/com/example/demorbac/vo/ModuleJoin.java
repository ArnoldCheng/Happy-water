package com.example.demorbac.vo;

import java.util.List;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 16:28 2018/10/4
 * @Modified By:
 */
public class ModuleJoin {
    private Long id;
    private String moduleName;
    private List<ModuleJoin> fathermodule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<ModuleJoin> getFathermodule() {
        return fathermodule;
    }

    public void setFathermodule(List<ModuleJoin> fathermodule) {
        this.fathermodule = fathermodule;
    }

    @Override
    public String toString() {
        return "ModuleJoin{" + "id=" + id + ", moduleName='" + moduleName + '\'' + ", fathermodule=" + fathermodule + '}';
    }
}
