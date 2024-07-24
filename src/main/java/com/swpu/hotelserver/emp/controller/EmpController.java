package com.swpu.hotelserver.emp.controller;


import com.alibaba.fastjson.JSONObject;
import com.swpu.hotelserver.common.result.Result;
import com.swpu.hotelserver.config.JwtComponet;
import com.swpu.hotelserver.emp.dto.LoginUser;
import com.swpu.hotelserver.emp.entity.Emp;
import com.swpu.hotelserver.emp.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hotel
 * @since 2024-07-24
 */
@RestController
@RequestMapping("/emp")
@Slf4j
public class EmpController {
    @Resource
    EmpService empService;
    @Autowired
    JwtComponet jwtComponet;
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginUser loginuser){
        //验证图片验证码
        //如果验证码正确不处理如果错误直接返回错误结果
        boolean result = empService.checkVcode(loginuser.getVcode());
        if (!result){
            return new Result<>().error("验证码错误");
        }
        else {
            //将前端发送的数据发给service处理
            Emp emp = empService.login(loginuser);
            //获取结果并封装，返回给前端
            if (ObjectUtils.isEmpty(emp)) {
                return new Result<>().error("用户名或密码错误");

            } else {
                //如果登录成功生成token
                String token = jwtComponet.sign(emp.getUsername(), emp.getPassword());
                //封装返回结果
                JSONObject obj=new JSONObject();
                obj.put("token",token);
                obj.put("userId",emp.getId());
                return new Result<>().success("登录成功").put(obj);
            }
        }
    }
    @GetMapping("/vcode")
    public Result<?> getVcode() {
        String code=empService.getVcode();
        return new Result<>().success().put(code);

    }

    @GetMapping("/info")
    public Result<?> getUserInfo(Integer userId){
        log.info("userId:{}",userId);
        Emp emp=empService.getById(userId);
        JSONObject obj=new JSONObject();
        if (ObjectUtils.isEmpty(emp)){
            return new Result<>().error();
        }
        else {
            obj.put("username",emp.getUsername());
            obj.put("imgUrl",emp.getImgUrl());
        }


        //data中的数据格式：data{"ussername:admin"}
        return  new Result<>().success().put(obj);

    }

}
