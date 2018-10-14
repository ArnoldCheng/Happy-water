package com.example.demorbac;

import com.example.demorbac.entity.Manager;
import com.example.demorbac.entity.Role;
import com.example.demorbac.mapper.ManagerMapper;
import com.example.demorbac.mapper.ModuleMapper;
import com.example.demorbac.mapper.RoleMapper;
import com.example.demorbac.utils.JwtUtil;
import com.example.demorbac.utils.Md5Util;
import com.example.demorbac.vo.ModuleJoin;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRbacApplicationTests {

    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private ModuleMapper moduleMapper;

    @Autowired(required = false)
    private ManagerMapper managerMapper;

    @Test
    public void contextLoads() {
        Gson gson = new Gson();
        List origin = new ArrayList();
        origin.add(12);
        origin.add("test");
        System.out.println("-----------------");
        System.out.println(gson.toJson(origin));
        System.out.println("-----------------");
        List list=gson.fromJson(gson.toJson(origin),List.class);
        for (Object o:list){
            System.out.println("-----------------");
            System.out.println(o);
            System.out.println("-----------------");
        }
    }

    @Test
    public void sqlTest(){
        long id=roleMapper.selectRoleIdByRoleName("用户");
        System.out.println("----------------------------");
        System.out.println(id);
        System.out.println("----------------------------");
    }

    @Test
    public void JwtTest(){
        redisTemplate.opsForValue().set("test",123456);
        System.out.println("-----------------------------");
        System.out.println(redisTemplate.opsForValue().get("test"));
        System.out.println("-----------------------------");
        redisTemplate.delete("test");
        System.out.println("-----------------------------");
        System.out.println(redisTemplate.opsForValue().get("test"));
        System.out.println("-----------------------------");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
        System.out.println(redisTemplate.delete("myKey").booleanValue());
        System.out.println("*****************************");
    }

    @Test
    public void moduleListTest(){
        System.out.println("---------------------------------");
        List<ModuleJoin> moduleJoinsOne=moduleMapper.selectTopModuleById(33,0);
        for(ModuleJoin moduleJoin:moduleJoinsOne){
            moduleJoin.setFathermodule(moduleMapper.selectTopModuleById(33,moduleJoin.getId()));
        }
        for(ModuleJoin moduleJoin:moduleJoinsOne){
            System.out.println("---------------------------------");
            System.out.println(moduleJoin.toString());
            System.out.println("---------------------------------");
        }
    }

    @Test
    public void MD5(){
        String password="123";
        String uuid= UUID.randomUUID()+"";
        String pwd=Md5Util.getMD5String(password+uuid);
        System.out.println("uuid: "+uuid);
        System.out.println("pwd: "+pwd);
    }

    @Test
    public void minRoleLevel(){
        System.out.println(managerMapper.selectManagerByAccount("admin"));
    }

    @Test
    public void getRoleLevelById(){
        Role role=roleMapper.selectRoleLevelById(38);
        System.out.println(role);
    }

    @Test
    public void getRoleLevelByRoleName(){
        int roleLevel=roleMapper.selectRoleLevelByRoleName("管理员");
        System.out.println(roleLevel);
    }

    @Test
    public void boolTest(){
        boolean a;
        Boolean a2=null;
    }
}
