package com.example.demo_for_security.dao;

import com.example.demo_for_security.pojo.SysRole;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

public interface SysRoleDao extends Mapper<SysRole>, IdsMapper<SysRole> {
}
