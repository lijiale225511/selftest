package com.example.selftest.service;

import com.example.selftest.dto.*;

public interface RubbishService {

    /**
     * @Description: 获取表格数据
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
   TableRspDTO list5Table(TableReqDTO tableReqDTO) throws Exception;

    TableRspDTO list6Table(TableReqDTO tableReqDTO) throws Exception;
    /**
     * @Description: 添加记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    Integer add(RubbishDTO rubbishDTO) throws Exception;

    /**
     * @Description: 编辑记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    Integer edit(RubbishDTO rubbishDTO) throws Exception;

    /**
     * @Description: 删除记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    Integer remove(Integer RubbishDTO) throws Exception;
}
