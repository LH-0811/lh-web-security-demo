package com.example.demo_for_security.dao;

import com.example.demo_for_security.pojo.SysRes;
import com.example.demo_for_security.pojo.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserDao extends Mapper<SysUser> {


    @Select("<script>\n" +
            "SELECT\n" +
            "\tres.* \n" +
            "FROM\n" +
            "\tsys_user AS u\n" +
            "\tLEFT JOIN sys_user_role AS ur ON u.id = ur.user_id\n" +
            "\tLEFT JOIN sys_role AS r ON ur.role_id = r.id\n" +
            "\tLEFT JOIN sys_role_res AS rr ON r.id = rr.role_id\n" +
            "\tLEFT JOIN sys_res AS res ON res.id = rr.res_id \n" +
            "WHERE\n" +
            "\tu.id =#{userId} \n" +
            "<if test='resType != null'> \n" +
                "AND res.type = #{resType}\n" +
            "</if>\n" +
            "</script>\n")
    List<SysRes> getUserResListByUserIdAndResType(@Param("userId") Long userId, @Param("resType") Integer resType);
}
