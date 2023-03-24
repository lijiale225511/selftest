package com.example.selftest.service;

import  com.example.selftest.dto.KaptchaDTO;
import  com.example.selftest.dto.OpResultDTO;
import  com.example.selftest.dto.UserDTO;


public interface LoginService {

    /**
     * @Description: 获取验证码信息
     * @Author: SN
     * @Date: 2020/02/31 11:02
     */
    KaptchaDTO getKaptcha() throws Exception;

    /**
     * @Description: 登录认证
     * @Author: SN
     * @Date: 2020/02/31 11:02
     */
    OpResultDTO loginCheck(String loginCode, String loginPwd, String randomId, String authCode) throws Exception;
    OpResultDTO loginCheck1(String loginCode, String loginPwd, String randomId, String authCode) throws Exception;

    /**
     * @Description: 根据登录账号获取用户信息
     * @Author: SN
     * @Date: 2020/02/31 11:02
     */
    UserDTO getByLoginCode(String loginCode) throws Exception;
    UserDTO getByLoginCode1(String loginCode) throws Exception;
}
