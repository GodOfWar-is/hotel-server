package com.swpu.hotelserver.config;

import com.swpu.hotelserver.common.exception.UnAuthException;
import com.swpu.hotelserver.emp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
/** 基于Interceptor的JWT身份认证流程
* 1.前端向后端发送登录请求
* 2后端验证用户和 密码
* 3验证成功使用JWT和token，返回前端
* 4前端保存至缓存
* 5但前端发送其他请求是需要输入token添加到请求头
* 6.后台定义Interceptor，使用oreHandle方法拦截，获取token验证
* 7后台验证失败抛异常，异常由ControllerAdvice处理并返回前端
* 8自定义的INterceptor要注册WebMvcConfigure中才能生效*/
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtComponet jwtComponet;
    @Autowired
    EmpService empService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(token)) {
           //抛token空异常
            throw new UnAuthException("token为空");
        }
        Date date= jwtComponet.getExpireAt(token);
        if(date.before(new Date())){
            //抛token过期异常
            throw new UnAuthException("token过期");
        }
        //还可以
//        * 1获取用户名，查询是否存在
        String username = jwtComponet.getUsername(token);
        String password=empService.getPasswordByusername(username);
        if(!jwtComponet.verify(token,username,password)){
            //抛token不合法异常
            throw new UnAuthException("token不合法异常");
        }
//        * 2存在就根据用户名和密码验证token是否合法



        return true;

    }

}
