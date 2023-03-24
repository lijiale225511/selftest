package com.example.selftest.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.example.selftest.dto.KaptchaDTO;
import com.example.selftest.dto.OpResultDTO;
import com.example.selftest.dto.TokenDTO;
import com.example.selftest.dto.UserDTO;
import com.example.selftest.mapper.UserMapper;
import com.example.selftest.service.LoginService;
import com.example.selftest.utils.JWTUtil;
import com.example.selftest.utils.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Base64.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class LoginServiceImpl implements LoginService {

    private final Producer kaptchaProducer;
    private final RedisUtil redisUtil;
    private final UserMapper userMapper;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper, Producer kaptchaProducer, RedisUtil redisUtil) {
        this.userMapper = userMapper;
        this.kaptchaProducer = kaptchaProducer;
        this.redisUtil = redisUtil;
    }

    // token有效时长，60 * 4，单位分钟，共4小时
    private Integer expire =600;

    @Override
    public KaptchaDTO getKaptcha() throws Exception {
        // 生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage bufferedImage = kaptchaProducer.createImage(text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        Encoder encoder = Base64.getEncoder();
        String base64 = encoder.encodeToString(outputStream.toByteArray());
        String captchaBase64 = "data:image/png;base64," + base64.replaceAll("\r\n", "");
        // 存入Redis数据库
        String randomId = RandomStringUtils.random(15, true, true);
        redisUtil.set(randomId, text, 60);
        // 生成验证码返回数据
        KaptchaDTO kaptchaDTO = new KaptchaDTO();
        kaptchaDTO.setUuid(randomId);
        kaptchaDTO.setImage(captchaBase64);
        kaptchaDTO.setExpire(60);
        return kaptchaDTO;
    }

    @Override
    public OpResultDTO loginCheck(String loginCode, String loginPwd, String randomId, String authCode) throws Exception {
        OpResultDTO opResult = new OpResultDTO();
        TokenDTO tokenDTO = new TokenDTO();
        if (randomId == null || redisUtil.get(randomId) == null) {
            opResult.setMsgResult("error");
            opResult.setObjResult(JSONObject.parseObject("{ 'info': '验证码已过期，请单击刷新' }"));
        } else if (!((String) redisUtil.get(randomId)).equalsIgnoreCase(authCode)) {
            opResult.setMsgResult("error");
            opResult.setObjResult(JSONObject.parseObject("{ 'info': '验证码错误' }"));
        } else {
            UserDTO userDTO = this.getByLoginCode(loginCode);
            if (userDTO == null || !userDTO.getLoginPwd().equals(loginPwd)) {
                opResult.setMsgResult("error");
                opResult.setObjResult(JSONObject.parseObject("{ 'info': '用户名或密码错误' }"));
            } else {
                // 生成签名DTO，包含账号(用户)ID
                tokenDTO.setLoginId(userDTO.getUserId());
                tokenDTO.setLoginCode(userDTO.getLoginCode());
                tokenDTO.setLoginName(userDTO.getUserName());
                opResult.setMsgResult("success");
                opResult.setObjResult(JWTUtil.createSign(tokenDTO.toString(), expire));
            }
        }
        return opResult;
    }
    @Override
    public OpResultDTO loginCheck1(String loginCode, String loginPwd, String randomId, String authCode) throws Exception {
        OpResultDTO opResult = new OpResultDTO();
        TokenDTO tokenDTO = new TokenDTO();
        if (randomId == null || redisUtil.get(randomId) == null) {
            opResult.setMsgResult("error");
            opResult.setObjResult(JSONObject.parseObject("{ 'info': '验证码已过期，请单击刷新' }"));
        } else if (!((String) redisUtil.get(randomId)).equalsIgnoreCase(authCode)) {
            opResult.setMsgResult("error");
            opResult.setObjResult(JSONObject.parseObject("{ 'info': '验证码错误' }"));
        } else {
            UserDTO userDTO1 = this.getByLoginCode1(loginCode);
            if (userDTO1 == null || !userDTO1.getLoginPwd().equals(loginPwd)) {
                opResult.setMsgResult("error");
                opResult.setObjResult(JSONObject.parseObject("{ 'info': '用户名或密码错误' }"));
            } else {
                // 生成签名DTO，包含账号(用户)ID
                tokenDTO.setLoginId(userDTO1.getUserId());
                tokenDTO.setLoginCode(userDTO1.getLoginCode());
                tokenDTO.setLoginName(userDTO1.getUserName());
                opResult.setMsgResult("success");
                opResult.setObjResult(JWTUtil.createSign(tokenDTO.toString(), expire));
            }
        }
        return opResult;
    }

    @Override
    public UserDTO getByLoginCode(String loginCode) throws Exception {
        return userMapper.getByLoginCode(loginCode);
    }
    @Override
    public UserDTO getByLoginCode1(String loginCode) throws Exception {
        return userMapper.getByLoginCode1(loginCode);
    }
}
