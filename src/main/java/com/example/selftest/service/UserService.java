package com.example.selftest.service;

import com.example.selftest.dto.UserDTO;
import com.example.selftest.dto.TableReqDTO;
import com.example.selftest.dto.TableRspDTO;

public interface UserService {

    /**
     * @Description: 获取表格数据
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    TableRspDTO list4Table(TableReqDTO tableReqDTO) throws Exception;
    TableRspDTO list3Table(TableReqDTO tableReqDTO) throws Exception;

    /**
     * @Description: 添加记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    Integer add(UserDTO userDTO) throws Exception;

    /**
     * @Description: 编辑记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    Integer edit(UserDTO userDTO) throws Exception;

    /**
     * @Description: 删除记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    Integer remove(Integer userDTO) throws Exception;
}
