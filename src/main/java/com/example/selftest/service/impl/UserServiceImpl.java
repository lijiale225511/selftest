package com.example.selftest.service.impl;

import com.example.selftest.controller.LoginController;
import com.example.selftest.controller.UserController;
import com.google.code.kaptcha.Producer;
import com.example.selftest.dto.*;
import com.example.selftest.entity.UserEntity;
import com.example.selftest.mapper.UserMapper;
import com.example.selftest.service.UserService;
import com.example.selftest.utils.RedisUtil;
import com.example.selftest.dto.LoginDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Producer kaptchaProducer;
    private final RedisUtil redisUtil;
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper, Producer kaptchaProducer, RedisUtil redisUtil) {
        this.userMapper = userMapper;
        this.kaptchaProducer = kaptchaProducer;
        this.redisUtil = redisUtil;
    }
    @Override
    public TableRspDTO list4Table(TableReqDTO tableReqDTO) throws Exception {
        Integer count = userMapper.count4Table(tableReqDTO.getQueryText());
        List<UserDTO> listUserDTOs = userMapper.list4Table(tableReqDTO.getStart(),
                tableReqDTO.getPageSize(), tableReqDTO.getQueryText());
        return new TableRspDTO(count, listUserDTOs);
    }
    @Override
    public TableRspDTO list3Table(TableReqDTO tableReqDTO) throws Exception {
        Integer count = 1;
        System.out.println(LoginController.loginCode);
        List<UserDTO> listUserDTOs = userMapper.list3Table(LoginController.loginCode);
        return new TableRspDTO(count, listUserDTOs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(UserDTO userDTO) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        UserEntity userEntity = mapperFactory.getMapperFacade().map(userDTO, UserEntity.class);
        userEntity.setUserType("2");
        userEntity.setGmtCreate(new Date());
        return userMapper.add(userEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer edit(UserDTO userDTO) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        UserEntity userEntity = mapperFactory.getMapperFacade().map(userDTO, UserEntity.class);
        return userMapper.edit(userEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer remove(Integer userId) throws Exception {
        return userMapper.remove(userId);
    }
}
