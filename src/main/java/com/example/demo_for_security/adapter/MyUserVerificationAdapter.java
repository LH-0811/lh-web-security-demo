package com.example.demo_for_security.adapter;

import com.example.demo_for_security.dao.SysRoleDao;
import com.example.demo_for_security.dao.SysUserDao;
import com.example.demo_for_security.dao.SysUserRoleDao;
import com.example.demo_for_security.enum_.ResType;
import com.example.demo_for_security.pojo.SysRes;
import com.example.demo_for_security.pojo.SysRole;
import com.example.demo_for_security.pojo.SysUser;
import com.example.demo_for_security.pojo.SysUserRole;
import com.lhit.starter.security.adapter.LhitSecurityUserAuthenticationAdapter;
import com.lhit.starter.security.exception.UserVerificationException;
import com.lhit.starter.security.pojo.LhitSecurityPermission;
import com.lhit.starter.security.pojo.LhitSecurityRole;
import com.lhit.starter.security.pojo.LhitSecurityUserPerms;
import com.lhit.starter.security.pojo.UsernamePasswordUserVerification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户认证适配器
 *
 * 通过该适配器 指定用户的登录认证过程 并将 用户的权限获取到
 */
@Component
public class MyUserVerificationAdapter implements LhitSecurityUserAuthenticationAdapter<UsernamePasswordUserVerification> {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public LhitSecurityUserPerms verification(UsernamePasswordUserVerification verification) throws Exception {
        SysUser query = new SysUser();
        query.setUsername(verification.getUsername());
        SysUser sysUser;
        try {
            sysUser = sysUserDao.selectOne(query);
        } catch (Exception e) {
            throw UserVerificationException.builder().username(verification.getUsername()).msg("用户名不存在").error(e).build();
        }

        if (sysUser == null)
            throw UserVerificationException.builder().username(verification.getUsername()).msg("用户名不存在").build();

        if (!sysUser.getPassword().equals(verification.getPassword()))
            throw UserVerificationException.builder().username(verification.getUsername()).msg("密码错误").build();

        List<SysRes> sysResList = sysUserDao.getUserResListByUserIdAndResType(sysUser.getId(), ResType.INTERFACE.getCode());
        SysUserRole queryUserRole = new SysUserRole();
        queryUserRole.setUserId(sysUser.getId());
        List<SysUserRole> userRoles = sysUserRoleDao.select(queryUserRole);
        List<SysRole> sysRoles = sysRoleDao.selectByIds(StringUtils.join(userRoles.stream().map(ele -> ele.getRoleId()).collect(Collectors.toList()), ","));

        List<LhitSecurityRole> roles = sysRoles.stream().map(ele -> new LhitSecurityRole(ele.getKey())).collect(Collectors.toList());

        List<LhitSecurityPermission> perms = sysResList.stream().map(ele -> LhitSecurityPermission.builder().permsCode(ele.getPerms()).url(ele.getUrl()).build()).collect(Collectors.toList());

        return  new LhitSecurityUserPerms(roles,perms,String.valueOf(sysUser.getId()));

    }
}
