package com.example.demorbac.controller;

import com.example.demorbac.entity.Manager;
import com.example.demorbac.entity.Result;
import com.example.demorbac.service.ManagerService;
import com.example.demorbac.utils.JwtUtil;
import com.example.demorbac.utils.Md5Util;
import com.example.demorbac.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: 快乐水 樱桃可乐
 * @Description:
 * @Date: Created in 16:29 2018/9/24
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/background/password",produces = "application/json")
public class PwdController {

    @Autowired
    ManagerService managerService;
    /**
     *
     * @Description: 后台：后台管理：密码管理：更换密码接口
     * @auther: 快乐水 樱桃可乐
     * @date: 16:40 2018/9/24
     * @param: [confirmPassword, newPassword, usedPassword]
     * @return: com.example.demorbac.entity.Result
     *
     */
    @PutMapping(value = "/password/{managerid}")
    public Result putPassword(@RequestParam String confirmPassword,
                              @RequestParam String newPassword,
                              @RequestParam String usedPassword,
                              @PathVariable long managerid,
                              HttpServletRequest request){
        if(ValidatorUtils.StringValidator(confirmPassword,newPassword,usedPassword)){
            return new Result(false,"密码不能为空",601);
        }
        if(ValidatorUtils.PwdValidator(newPassword)){
            return new Result(false,"新密码格式错误，请重试。",602);
        }
        if(ValidatorUtils.pwdCorrespond(newPassword,usedPassword)){
            return new Result(false,"新密码二次输入不一致，请重试。",604);
        }

        String jwt=request.getHeader("JWT");
        Map map=JwtUtil.parseJWT(jwt);
        Integer backstageId=(Integer) map.get("backstageId");
        //判断被修改密码的账户与当前操作者的一致性
        if(backstageId!=managerid){
            return new Result(false,"修改的账户与当前操作者不符");
        }
        //根据账号id查询密码与盐
        Manager managerDB=managerService.selectPasswordAndSaltByManagerId(managerid);
        //判断原来的密码是否正确
        if(!managerDB.getPassword().equals(Md5Util.getMD5String(confirmPassword+managerDB.getSalt()))){
            return new Result(false,"密码错误");
        }

        Manager manager=new Manager();
        manager.setId(managerid);
        manager.setSalt(UUID.randomUUID()+"");
        manager.setPassword(Md5Util.getMD5String(newPassword+manager.getSalt()));

        managerService.updatePwdById(manager);
        return new Result(true,"更换密码成功");
    }
}
