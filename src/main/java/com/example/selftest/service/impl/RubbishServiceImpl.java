package com.example.selftest.service.impl;

import com.example.selftest.controller.LoginController;
import com.example.selftest.controller.UserController;
import com.example.selftest.entity.RubbishEntity;
import com.example.selftest.service.RubbishService;
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
public class RubbishServiceImpl implements RubbishService {

    private final Producer kaptchaProducer;
    private final RedisUtil redisUtil;
    private final UserMapper userMapper;
    @Autowired
    public RubbishServiceImpl(UserMapper userMapper, Producer kaptchaProducer, RedisUtil redisUtil) {
        this.userMapper = userMapper;
        this.kaptchaProducer = kaptchaProducer;
        this.redisUtil = redisUtil;
    }
    @Override
    public TableRspDTO list5Table(TableReqDTO tableReqDTO) throws Exception {
        Integer count = userMapper.count5Table(tableReqDTO.getQueryText());
        List<RubbishDTO> listRubbishDTOs = userMapper.list5Table(tableReqDTO.getStart(),
                tableReqDTO.getPageSize(), tableReqDTO.getQueryText());
        return new TableRspDTO(count, listRubbishDTOs);
    }
    public TableRspDTO list6Table(TableReqDTO tableReqDTO) throws Exception {
        Integer count = userMapper.count6Table(LoginController.loginCode);
        List<RubbishDTO> listRubbishDTOs = userMapper.list5Table(tableReqDTO.getStart(),
                tableReqDTO.getPageSize(),LoginController.loginCode);
        return new TableRspDTO(count, listRubbishDTOs);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(RubbishDTO rubbishDTO) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        RubbishEntity rubbishEntity = mapperFactory.getMapperFacade().map(rubbishDTO, RubbishEntity.class);
        rubbishEntity.setGmtCreate(new Date());
        return userMapper.add1(rubbishEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer edit(RubbishDTO rubbishDTO) throws Exception {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
            RubbishEntity rubbishEntity = mapperFactory.getMapperFacade().map(rubbishDTO,RubbishEntity.class);
        return userMapper.edit1(rubbishEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer remove(Integer rubbishId) throws Exception {
        return userMapper.remove1(rubbishId);
    }
}
