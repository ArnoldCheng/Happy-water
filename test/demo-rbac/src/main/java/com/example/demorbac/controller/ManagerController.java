package com.example.demorbac.controller;

import com.example.demorbac.entity.Manager;
import com.example.demorbac.entity.Result;
import com.example.demorbac.entity.managerValidateGroup.UpAccountGroup;
import com.example.demorbac.service.Impl.ManagerServiceImpl;
import com.example.demorbac.entity.managerValidateGroup.AddAccountGroup;
import com.example.demorbac.utils.JwtUtil;
import com.example.demorbac.vo.ManagerDynamic;
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
 * @Date: Created in 22:01 2018/9/23
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/background/account",produces = "application/json")
public class ManagerController {

    @Autowired
    private ManagerServiceImpl managerService;

    /**
     *
     * @Description: 后台-后台管理-账户管理-分页列表
     * @auther: 快乐水 樱桃可乐
     * @date: 10:34 2018/9/25
     * @param: [pageNum, pageSize]
     * @return: java.util.List<com.example.demorbac.entity.Manager>
     *
     */
    @GetMapping(value = "/accounts")
    public Result getAccounts(@Range(min=1,message = "页码必须是大于1的数字")
                                  @RequestParam int pageNum){
            return new Result<>(true, managerService.selectAll(pageNum));
    }

    /**
     *
     * @Description: 后台-后台管理-账户管理-多条件查询
     * @auther: 快乐水 樱桃可乐
     * @date: 9:13 2018/9/27 
     * @param: [pageNum, serialId, roleName, account, createdBy]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @GetMapping(value = "/accountssearch")
    public Result getAccountsSearch(@Range(min=1,message = "页码必须是大于1的数字")
                                        @RequestParam(value = "pageNum",required = false,defaultValue ="1" ) Integer pageNum,
                                    @RequestParam(value = "serialId",required = false) String serialId,
                                    @RequestParam(value = "roleName",required = false) String roleName,
                                    @RequestParam(value = "account",required = false) String account,
                                    @RequestParam(value = "createdBy",required = false) String createdBy){

        ManagerDynamic managerDynamic=new ManagerDynamic();
        managerDynamic.setRoleName(roleName);
        managerDynamic.setSerialId(serialId);
        managerDynamic.setAccount(account);
        managerDynamic.setCreatedBy(createdBy);
        return new Result(true,managerService.selectDynamic(managerDynamic,pageNum));
    }

    /**
     *
     * @Description: 后台-后台管理-账户管理-添加账户
     * @auther: 快乐水 樱桃可乐
     * @date: 15:15 2018/9/27
     * @param: [json]:账户名/密码/角色类集合/状态
     * @return: com.example.demorbac.entity.Result
     *
     */
    @PostMapping(value = "/account")
    public Result postAccount(@Validated(AddAccountGroup.class)
                                  @RequestBody Manager manager,
                              BindingResult result,
                              HttpServletRequest request){
        //获取JWT中的荷载Map
        Map map=JwtUtil.parseJWT(request.getHeader("JWT"));
        //添加账户
        return managerService.addManager(manager,map);
    }

    /**
     *
     * @Description: 后台管理-账户管理-更新用户
     * @auther: 快乐水 樱桃可乐
     * @date: 4:07 2018/10/3
     * @param: [manager, result, request]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @PutMapping(value = "/account/{id:\\d+}")
    public Result putAccount(@Validated(UpAccountGroup.class)
                                 @RequestBody Manager manager,
                              BindingResult result,
                             @PathVariable long id,
                             HttpServletRequest request){
        //获取JWT中的荷载Map
        Map map=JwtUtil.parseJWT(request.getHeader("JWT"));
        //设置id属性
        manager.setId(id);
        //更新账户
        return managerService.updateManager(manager,map);
    }

    /**
     *
     * @Description: 后台-账户-删除
     * @auther: 快乐水 樱桃可乐
     * @date: 9:43 2018/10/3
     * @param: [id]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @DeleteMapping(value = "/account/{id:\\d+}")
    public Result DeleteAccount(@PathVariable long id,
                                HttpServletRequest request){
        Map map=JwtUtil.parseJWT(request.getHeader("JWT"));
        return managerService.deleteManager(id,map);
    }

    /**
     *
     * @Description:  获取角色列表
     * @auther: 快乐水 樱桃可乐
     * @date: 10:41 2018/10/3
     * @param: []
     * @return: com.example.demorbac.entity.Result
     *
     */
    @GetMapping(value = "/roles")
    public Result GetAccountRole(){
        return new Result<>(true, managerService.selectRoleName());
    }
}
