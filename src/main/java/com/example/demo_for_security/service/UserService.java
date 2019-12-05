package com.example.demo_for_security.service;


import com.example.demo_for_security.pojo.SysRes;
import com.example.demo_for_security.pojo.SysUser;

import java.util.List;

public interface UserService {
    SysUser getSysUserByUsername(String username);

    List<SysRes> getUserResListByUserIdAndResType(Long userId, Integer resType);
}
