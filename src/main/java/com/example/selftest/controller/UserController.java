package com.example.selftest.controller;

import com.example.selftest.dto.OpResultDTO;
import com.example.selftest.dto.UserDTO;
import com.example.selftest.dto.TableReqDTO;
import com.example.selftest.dto.TableRspDTO;
import com.example.selftest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired(required = true)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * @Description: 获取数据列表
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public TableRspDTO list3Table(TableReqDTO tableReqDTO) {
        TableRspDTO tableRspDTO = new TableRspDTO();
        try {
            tableRspDTO = userService.list3Table(tableReqDTO);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return tableRspDTO;
    }



    /**
     * @Description: 添加记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public OpResultDTO add(UserDTO userDTO) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult.setMsgResult("success");
            opResult.setObjResult(userService.add(userDTO));
        } catch (Exception e) {
            logger.error(e.toString());
            opResult.setMsgResult("error");
        }
        return opResult;
    }

    /**
     * @Description: 编辑记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public OpResultDTO update(UserDTO userDTO) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult.setMsgResult("success");
            opResult.setObjResult(userService.edit(userDTO));
        } catch (Exception e) {
            logger.error(e.toString());
            opResult.setMsgResult("error");
        }
        return opResult;
    }

    /**
     * @Description: 删除记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public OpResultDTO remove(Integer userId) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult.setMsgResult("success");
            opResult.setObjResult(userService.remove(userId));
        } catch (Exception e) {
            logger.error(e.toString());
            opResult.setMsgResult("error");
        }
        return opResult;
    }
}
