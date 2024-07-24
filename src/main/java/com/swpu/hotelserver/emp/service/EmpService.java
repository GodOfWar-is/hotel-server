package com.swpu.hotelserver.emp.service;

import com.swpu.hotelserver.emp.dto.LoginUser;
import com.swpu.hotelserver.emp.entity.Emp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hotel
 * @since 2024-07-24
 */
public interface EmpService extends IService<Emp> {
    Emp login(LoginUser loginuser);

    String getVcode();

    boolean checkVcode(String vocode);

    String getPasswordByusername(String username);

}
