package com.swpu.hotelserver.emp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.swpu.hotelserver.emp.dto.LoginUser;
import com.swpu.hotelserver.emp.entity.Emp;
import com.swpu.hotelserver.emp.mapper.EmpMapper;
import com.swpu.hotelserver.emp.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hotel
 * @since 2024-07-24
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    @Resource
    private EmpMapper empmapper;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Emp login(LoginUser loginuser) {
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("username",loginuser.getUsername()).eq("password",loginuser.getPassword());
        Emp one = this.getOne(wrapper);
        //lamba表达式左边时方法名称右边是方法的方法体
//        wrapper.and(qw->qw.eq().or().eq())
        Emp user = empmapper.selectOne(wrapper);
        return user;
    }

    @Override
    public String getVcode() {
        //生成验证码文本
        String text = defaultKaptcha.createText();
        //更具文本生成验证码图片
        BufferedImage image = defaultKaptcha.createImage(text);
        //将验证码文本保存到redis
        stringRedisTemplate.opsForValue().set(text, text,15, TimeUnit.MINUTES);
        //将验证码图片转把base64编码
        ByteArrayOutputStream bi=new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", bi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String code ="data:image/jpeg;base64," + Base64Utils.encodeToString(bi.toByteArray());
        return code;
    }

    @Override
    public boolean checkVcode(String vocode) {
        //更具参数验证码到redis中获取数据，如果能获取到表示验证成功
        String text = stringRedisTemplate.opsForValue().get(vocode);
        if (ObjectUtils.isEmpty(text)){
            return false;
        }
        return true;
    }

    @Override
    public String getPasswordByusername(String username) {
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Emp one = this.getOne(wrapper);

        return one.getPassword();
    }
}
