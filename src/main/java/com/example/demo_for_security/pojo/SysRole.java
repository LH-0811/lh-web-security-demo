package com.example.demo_for_security.pojo;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Table(name = "sys_role")
public class SysRole {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`key`")
    private String key;

    private Date createTime;

    private Date updateTime;

    private Long createBy;

    private Long updateBy;

}
