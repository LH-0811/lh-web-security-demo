package com.example.demo_for_security.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name = "sys_user_role")
public class SysUserRole {

    private Long userId;

    private Long roleId;

}
