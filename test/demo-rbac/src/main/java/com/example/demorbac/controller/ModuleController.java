package com.example.demorbac.controller;

import com.example.demorbac.entity.Module;
import com.example.demorbac.entity.Result;
import com.example.demorbac.entity.managerValidateGroup.AddAccountGroup;
import com.example.demorbac.entity.moduleValidateGroup.AddModuleGroup;
import com.example.demorbac.service.Impl.ModuleServiceImpl;
import com.example.demorbac.utils.JwtUtil;
import com.example.demorbac.vo.ModuleDynamic;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 10:57 2018/9/24
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/background/module",produces = "application/json")
public class ModuleController {

    @Autowired
    private ModuleServiceImpl moduleService;

    /**
     *
     * @Description: 后台-模块-列表
     * @auther: 快乐水 樱桃可乐
     * @date: 2:29 2018/10/4
     * @param: [pageNum]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @GetMapping(value = "/modules")
    public Result getModules(@Range(min=1,message = "页码必须是大于1的数字")
                                       @RequestParam int pageNum){
       return new Result<>(true,moduleService.selectAll(pageNum));
    }

    /**
     *
     * @Description: 后台-模块-多条件
     * @auther: 快乐水 樱桃可乐
     * @date: 9:09 2018/10/4
     * @param: [moduleID, moduleName, moduleFatherName, createdBy, pageNum]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @GetMapping(value = "/modulessearch")
    public Result getModulesSearch(@RequestParam(name = "serialId",required = false) String moduleID,
                                   @RequestParam(name = "moduleName",required = false) String moduleName,
                                   @RequestParam(name = "moduleFatherName",required = false) String moduleFatherName,
                                   @RequestParam(name = "createdBy",required = false) String createdBy,
                                   @Range(min=1,message = "页码必须是大于1的数字")
                                       @RequestParam int pageNum){
        ModuleDynamic moduleDynamic=new ModuleDynamic();
        moduleDynamic.setModuleId(moduleID);
        moduleDynamic.setCreatedBy(createdBy);
        moduleDynamic.setModuleFatherName(moduleFatherName);
        moduleDynamic.setModuleName(moduleName);
        System.out.println(moduleDynamic);
        return new Result<>(true,moduleService.selectDynamic(moduleDynamic,pageNum));
    }

    /**
     *
     * @Description: 后台-模块-添加模块
     * @auther: 快乐水 樱桃可乐
     * @date: 15:27 2018/10/4
     * @param: [module, result, request]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @PostMapping(value = "/module")
    public Result postModule(@Validated(AddModuleGroup.class)
                                 @RequestBody Module module,
                             BindingResult result,
                             HttpServletRequest request){
        //获取荷载
        Map map=JwtUtil.parseJWT(request.getHeader("JWT"));
        return moduleService.addModule(module,map);
    }

    /**
     *
     * @Description: 后台-模块-更新模块
     * @auther: 快乐水 樱桃可乐
     * @date: 15:27 2018/10/4
     * @param: [module, id, result, request]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @PutMapping(value = "/module/{id:\\d+}")
    public Result putModule(@RequestBody Module module,
                             @PathVariable long id,
                             BindingResult result,
                             HttpServletRequest request){
        //获取荷载
        Map map=JwtUtil.parseJWT(request.getHeader("JWT"));
        //设置id
        module.setId(id);
        return moduleService.updateModule(module,map);
    }

    /**
     *
     * @Description: 后台-模块-删除模块
     * @auther: 快乐水 樱桃可乐
     * @date: 15:27 2018/10/4
     * @param: [id]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @DeleteMapping(value = "/module/{id:\\d+}")
    public Result deleteModule(@PathVariable long id){
        return moduleService.deleteModule(id);
    }

}
