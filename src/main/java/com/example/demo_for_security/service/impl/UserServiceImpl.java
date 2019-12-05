package com.example.demo_for_security.service.impl;

import com.example.demo_for_security.dao.SysUserDao;
import com.example.demo_for_security.pojo.SysRes;
import com.example.demo_for_security.pojo.SysUser;
import com.example.demo_for_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserDao sysUserDao;


    @Override
    public SysUser getSysUserByUsername(String username) {
        SysUser query = new SysUser();
        query.setUsername(username);
        return sysUserDao.selectOne(query);
    }

    @Override
    public List<SysRes> getUserResListByUserIdAndResType(Long userId, Integer resType) {
        return sysUserDao.getUserResListByUserIdAndResType(userId, resType);
    }

}
