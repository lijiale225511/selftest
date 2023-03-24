package com.example.selftest.controller;
import com.example.selftest.dto.*;
import com.example.selftest.service.RubbishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin_rubbish")
public class admin_RubbishController {

    private final RubbishService rubbishService;

    @Autowired(required = true)
    public admin_RubbishController(RubbishService rubbishService) {
        this.rubbishService = rubbishService;
    }

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * @Description: 获取数据列表
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public TableRspDTO list5Table(TableReqDTO tableReqDTO) {
        TableRspDTO tableRspDTO = new TableRspDTO();
        try {
            tableRspDTO = rubbishService.list5Table(tableReqDTO);
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
    public OpResultDTO add(RubbishDTO  rubbishDTO) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult.setMsgResult("success");
            opResult.setObjResult(rubbishService.add(rubbishDTO));
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
    public OpResultDTO update(RubbishDTO rubbishDTO) {
        OpResultDTO opResult = new OpResultDTO();
        try {
            opResult.setMsgResult("success");
            opResult.setObjResult(rubbishService.edit(rubbishDTO));
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
            opResult.setObjResult(rubbishService.remove(userId));
        } catch (Exception e) {
            logger.error(e.toString());
            opResult.setMsgResult("error");
        }
        return opResult;
    }
}

