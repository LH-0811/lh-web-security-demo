package com.example.demo_for_security.pojo;

import com.lhit.starter.security.pojo.user.LhitSecurityUser;
import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Table(name = "sys_user")
public class SysUser implements LhitSecurityUser {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String username;

    private String nickName;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Long createBy;

    private Long updateBy;

    private Date lastLogin;

    @Override
    public String getUserId() {
        return String.valueOf(id);
    }
}
