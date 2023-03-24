package com.example.selftest.controller;

import com.example.selftest.dto.KaptchaDTO;
import com.example.selftest.dto.LoginDTO;
import com.example.selftest.dto.OpResultDTO;
import com.example.selftest.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController {

    private final LoginService loginService;
    public static String loginCode;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * @Description: 获取验证码
     * @Author: SN
     * @Date: 2020/11/31 11:02
     */
    @RequestMapping(path = "/kaptcha", method = RequestMethod.GET)
    public OpResultDTO getKaptcha(HttpServletResponse response) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            KaptchaDTO kaptchaDTO = loginService.getKaptcha();
            opResult.setMsgResult("success");
            opResult.setObjResult(kaptchaDTO);
        } catch (Exception e) {
            logger.error(e.toString());
            opResult.setMsgResult("error");
        }
        return opResult;
    }

    /**
     * @Description: 用户账号登录验证
     * @Author: SN
     * @Date: 2020/02/31 11:02
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public OpResultDTO loginCheck(@RequestBody LoginDTO loginDTO) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult = loginService.loginCheck(
                    loginDTO.getLoginCode(),
                    loginDTO.getLoginPwd(),
                    loginDTO.getRandomUuid(),
                    loginDTO.getAuthCode());
            loginCode = loginDTO.getLoginCode();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return opResult;
    }
    @RequestMapping(value = "/check1", method = RequestMethod.POST)
    public OpResultDTO loginCheck1(@RequestBody LoginDTO loginDTO) {
        OpResultDTO opResult1 = new OpResultDTO();
        try {
            opResult1 = loginService.loginCheck1(
                    loginDTO.getLoginCode(),
                    loginDTO.getLoginPwd(),
                    loginDTO.getRandomUuid(),
                    loginDTO.getAuthCode());
            loginCode = loginDTO.getLoginCode();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return opResult1;
    }
    /**
     * @Description: Token验证
     * @Author: SN
     * @Date: 2020/02/31 11:02
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public OpResultDTO tokenCheck(@RequestHeader String token) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            // 演示一下，返回token好了，拦截器已经处理过了。·
            opResult.setMsgResult(token);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return opResult;
    }
}
