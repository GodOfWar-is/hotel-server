package com.swpu.hotelserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
        *addResourceHandler设置url映射路径
        * addResourceLocations文件实际本地目录 */
        registry.addResourceHandler("/view/**")
                .addResourceLocations("file:///E:\\Test\\springo\\files\\");
    }
/*addPathPatterns那些url使用拦截器
* excludePathPatterns那些url不使用拦截器
* */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/emp/login","/emp/vcode","/emp/img","/view/**");
    }
}
