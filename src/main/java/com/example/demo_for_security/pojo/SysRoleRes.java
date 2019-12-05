package com.example.demo_for_security.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Table(name = "sys_role_res")
public class SysRoleRes {

    private Long roleId;

    private Long resId;

}
