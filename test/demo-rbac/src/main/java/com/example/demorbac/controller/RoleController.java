package com.example.demorbac.controller;

import com.example.demorbac.entity.Result;
import com.example.demorbac.entity.Role;
import com.example.demorbac.entity.roleValidateGroup.AddRoleGroup;
import com.example.demorbac.entity.roleValidateGroup.UpRoleGroup;
import com.example.demorbac.service.Impl.RoleServiceImpl;
import com.example.demorbac.service.RoleService;
import com.example.demorbac.utils.JwtUtil;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 9:37 2018/9/24
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/background/role",produces = "application/json")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    /**
     *
     * @Description: 查询角色列表
     * @auther: 快乐水 樱桃可乐
     * @date: 16:09 2018/10/3
     * @param: [pageNum]
     * @return: java.util.List<com.example.demorbac.entity.Role>
     *
     */
    @GetMapping(value = "/roles")
    public Result getRoles(@Range(min=1,message = "页码必须是大于1的数字") @RequestParam int pageNum){
        return new Result<>(true,roleService.selectAll(pageNum));
    }

    /**
     *
     * @Description: 多条件查询角色
     * @auther: 快乐水 樱桃可乐
     * @date: 16:39 2018/10/3
     * @param: [serialId, roleName, updatedBy, CreatedBy, pageNumber]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @GetMapping(value = "/rolessearch")
    public Result getRolesSearch(@RequestParam(name = "serialId",required = false)String serialId,
                                 @RequestParam(name = "roleName",required = false)String roleName,
                                 @RequestParam(name = "updatedBy",required = false)String updatedBy,
                                 @RequestParam(name = "createdBy",required = false)String createdBy,
                                 @Range(min=1,message = "页码必须是大于1的数字")
                                     @RequestParam(name = "pageNumber",defaultValue = "1")int pageNumber){
        Role role=new Role();
        role.setRoleName(roleName);
        role.setSerialId(serialId);
        role.setCreatedBy(createdBy);
        role.setUpdatedBy(updatedBy);
        return new Result<>(true,roleService.selectDynamic(role,pageNumber));
    }

    /**
     *
     * @Description: 后台-角色-添加角色
     * @auther: 快乐水 樱桃可乐
     * @date: 20:27 2018/10/3
     * @param: [role, request] 角色名 模块(权限)
     * @return: com.example.demorbac.entity.Result
     *
     */
    @PostMapping(value = "/role")
    public Result postRole(@Validated(AddRoleGroup.class) @RequestBody Role role,
                           BindingResult result,
                           HttpServletRequest request){
        Map map=JwtUtil.parseJWT(request.getHeader("JWT"));
        return roleService.addRole(role,map);
    }

    /**
     *
     * @Description: 后台-角色-编辑角色
     * @auther: 快乐水 樱桃可乐
     * @date: 20:48 2018/10/3
     * @param: [role, bindingResult, id, request] 模块(权限) id 角色名
     * @return: com.example.demorbac.entity.Result
     *
     */
    @PutMapping(value = "/role/{id:\\d+}")
    public Result putRole(@Validated(UpRoleGroup.class) @RequestBody Role role,
                           BindingResult bindingResult,
                           @PathVariable long id,
                           HttpServletRequest request){
        Map map=JwtUtil.parseJWT(request.getHeader("JWT"));
        role.setId(id);
        return roleService.updateRole(role,map);
    }

    /**
     *
     * @Description: 后台-角色-删除角色
     * @auther: 快乐水 樱桃可乐
     * @date: 14:30 2018/10/5
     * @param: [id]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @DeleteMapping(value = "/role/{id:\\d+}")
    public Result deleteRole(@PathVariable long id,HttpServletRequest request){
        Map map=JwtUtil.parseJWT(request.getHeader("JWT"));
        return roleService.deleteRole(id,map);
    }
}
