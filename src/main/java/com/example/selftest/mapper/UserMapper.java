package com.example.selftest.mapper;

import com.example.selftest.dto.RubbishDTO;
import com.example.selftest.dto.UserDTO;
import com.example.selftest.entity.RubbishEntity;
import com.example.selftest.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    /**
     * @Description: 根据用户登录名称获取用户信息
     * @Author: SN
     * @Date: 2019/01/30 18:15
     */
    @Select({"SELECT " +
            "user_id, login_code, login_pwd, user_name, " +
            "user_email, user_phone, user_type, gmt_create " +
            "FROM tb_user " +
            "WHERE login_code = #{loginCode} OR " +
            "user_email = #{loginCode} OR " +
            "user_phone = #{loginCode} LIMIT 1"
    })
    UserDTO getByLoginCode(@Param("loginCode") String loginCode) throws Exception;

    @Select({"SELECT " +
            "manger_id, login_code, login_pwd, manger_name, " +
            "manger_email, manger_phone, manger_type, gmt_create " +
            "FROM tb_manger " +
            "WHERE login_code = #{loginCode} OR " +
            "manger_email = #{loginCode} OR " +
            "manger_phone = #{loginCode} LIMIT 1"
    })
    UserDTO getByLoginCode1(@Param("loginCode") String loginCode) throws Exception;
    /**
     * @Description: 获取表格数据
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    //垃圾表查询
    @Select({"SELECT " +
            "rubbish_id, login_code, rubbish_name, " +
            "telephone, rubbish_type,rubbish_image,gmt_create " +
            "FROM tb_rubbish " +
            "WHERE rubbish_id = #{i}"})
    RubbishDTO se1(@Param("i") Integer rubbishId) throws Exception;
    @Select({"SELECT " +
            "rubbish_id, login_code, rubbish_name, " +
            "telephone, rubbish_type, gmt_create " +
            "FROM tb_rubbish " +
            "WHERE login_code LIKE CONCAT('%', #{queryText}, '%')" +
            "ORDER BY rubbish_id asc " +
            "LIMIT #{start}, #{length}"})
    List<RubbishDTO> list5Table(@Param("start") Integer start,
                                @Param("length") Integer length,
                                @Param("queryText") String queryText) throws Exception;
    @Select({"SELECT " +
            "rubbish_id, login_code, rubbish_name, " +
            "telephone, rubbish_type, gmt_create " +
            "FROM tb_rubbish " +
            "WHERE login_code = #{logincode}"})
    List<RubbishDTO> list6Table(@Param("logincode") String loginCode) throws Exception;
    //用户表查询
    @Select({"SELECT " +
            "user_id, login_code, login_pwd, user_name, " +
            "user_email, user_phone, user_type, gmt_create " +
            "FROM tb_user " +
            "WHERE login_code LIKE CONCAT('%', #{queryText}, '%') OR user_name LIKE CONCAT('%', #{queryText}, '%') " +
            "ORDER BY user_id asc " +
            "LIMIT #{start}, #{length}"})
    List<UserDTO> list4Table(@Param("start") Integer start,
                             @Param("length") Integer length,
                             @Param("queryText") String queryText) throws Exception;
    // queryText: 查询文本为登陆账号；根据login_code查询用户信息
    @Select({"SELECT " +
            "user_id, login_code, login_pwd, user_name, " +
            "user_email, user_phone, user_type, gmt_create " +
            "FROM tb_user " +
            "WHERE login_code = #{queryText} LIMIT 1"
    })
    List<UserDTO> list3Table(@Param("queryText") String queryText) throws Exception;

    /**
     * @Description: 获取表格数据记录的总条数
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @Select({"<script>SELECT COUNT(*) " +
            "FROM tb_user " +
            "WHERE login_code LIKE CONCAT('%', #{queryText}, '%') OR user_name LIKE CONCAT('%', #{queryText}, '%')</script>"})
    Integer count4Table(@Param("queryText") String queryText) throws Exception;
    @Select({"<script>SELECT COUNT(*) " +
            "FROM tb_rubbish " +
            "WHERE login_code LIKE CONCAT('%', #{queryText}, '%')</script>"})
    Integer count5Table(@Param("queryText") String queryText) throws Exception;
    @Select({"<script>SELECT COUNT(*) " +
            "FROM tb_rubbish " +
            "WHERE login_code LIKE CONCAT('%', #{queryText}, '%')</script>"})
    Integer count6Table(@Param("queryText") String queryText) throws Exception;
    /**
     * @Description: 添加记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @Insert("INSERT INTO tb_user ( " +
            "login_code, login_pwd, user_name, " +
            "user_email, user_phone, " +
            "user_type, gmt_create) " +
            "VALUES (#{userEntity.loginCode}, #{userEntity.loginPwd}, #{userEntity.userName}, " +
            "#{userEntity.userEmail}, #{userEntity.userPhone}, " +
            "#{userEntity.userType}, #{userEntity.gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    Integer add(@Param("userEntity") UserEntity userEntity) throws Exception;
    @Insert("INSERT INTO tb_rubbish ( " +
            "login_code, rubbish_name, " +
            " telephone, " +
            "rubbish_type, gmt_create) " +
            "VALUES (#{rubbishEntity.loginCode}, #{rubbishEntity.rubbishName}, " +
            "#{rubbishEntity.telePhone}, " +
            "#{rubbishEntity.rubbishType}, #{rubbishEntity.gmtCreate})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    Integer add1(@Param("rubbishEntity") RubbishEntity rubbishEntity) throws Exception;

    /**
     * @Description: 编辑记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @Update("UPDATE tb_user " +
            "SET user_name = #{userEntity.userName}, " +
            "user_email = #{userEntity.userEmail}, " +
            "user_phone = #{userEntity.userPhone} " +
            "WHERE user_id = #{userEntity.userId}")
    Integer edit(@Param("userEntity") UserEntity userEntity) throws Exception;

    @Update("UPDATE tb_rubbish " +
            "SET rubbish_name = #{rubbishEntity.rubbishName}, " +
            "telephone = #{rubbishEntity.telePhone}, " +
            "rubbish_type = #{rubbishEntity.rubbishType} " +
            "rubbish_image = #{rubbishEntity.rubbishImage} " +
            "WHERE rubbish_id = #{rubbishEntity.rubbishId}")
    Integer edit1(@Param("rubbishEntity") RubbishEntity rubbishEntity) throws Exception;
    @Update("UPDATE tb_rubbish " +
            "SET rubbish_image = #{rubbishEntity.rubbishImage} " +
            "WHERE rubbish_id = #{rubbishEntity.rubbishId}")
    Integer edit2(@Param("rubbishEntity") RubbishEntity rubbishEntity) throws Exception;

    /**
     * @Description: 删除记录
     * @Author: SN
     * @Date: 2021/11/03 18:15
     */
    @Delete({"DELETE FROM tb_user WHERE user_id = #{userId}"})
    Integer remove(@Param("userId") Integer userId) throws Exception;
    @Delete({"DELETE FROM tb_rubbish WHERE rubbish_id = #{rubbishId}"})
    Integer remove1(@Param("rubbishId") Integer rubbishId) throws Exception;
}
