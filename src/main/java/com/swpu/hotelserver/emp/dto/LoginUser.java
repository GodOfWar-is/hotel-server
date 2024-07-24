package com.swpu.hotelserver.emp.dto;

import lombok.Data;

@Data
public class LoginUser {
    private String username;
    private String password;
    private String vcode;
}

