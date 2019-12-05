package com.example.demo_for_security.pojo;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Table(name = "sys_res")
public class SysRes {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private Long pid;

    private String name;

    private Integer type;

    private String perms;

    private String path;

    private String url;

    private String icon;

    private String compent;

    private Boolean activeFlag;

    private Integer orderNum;

    private Date createTime;

    private Date updateTime;

    private Long createBy;

    private Long updateBy;
}
