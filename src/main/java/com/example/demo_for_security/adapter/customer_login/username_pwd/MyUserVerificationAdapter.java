package com.example.demo_for_security.adapter.customer_login.username_pwd;

import com.example.demo_for_security.dao.SysRoleDao;
import com.example.demo_for_security.dao.SysUserDao;
import com.example.demo_for_security.dao.SysUserRoleDao;
import com.example.demo_for_security.enum_.ResType;
import com.example.demo_for_security.pojo.SysRes;
import com.example.demo_for_security.pojo.SysRole;
import com.example.demo_for_security.pojo.SysUser;
import com.example.demo_for_security.pojo.SysUserRole;
import com.lhit.starter.security.defense.adapter.LhitSecurityUserVerificationAdapter;
import com.lhit.starter.security.defense.annotation.LhitUserVerification;
import com.lhit.starter.security.defense.exception.UserVerificationException;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityPermission;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityRole;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityUserPerms;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户认证适配器
 * <p>
 * 通过该适配器 指定用户的登录认证过程 并将 用户的权限获取到
 */
@LhitUserVerification
public class MyUserVerificationAdapter implements LhitSecurityUserVerificationAdapter<UsernamePasswordUserVerification> {

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
            throw UserVerificationException.create(verification.getUsername(),"用户名不存在");
        }

        if (sysUser == null)
            throw UserVerificationException.create(verification.getUsername(),"用户名不存在");

        if (!sysUser.getPassword().equals(verification.getPassword()))
            throw UserVerificationException.create(verification.getUsername(),"密码错误");

        List<SysRes> sysResList = sysUserDao.getUserResListByUserIdAndResType(sysUser.getId(), ResType.INTERFACE.getCode());
        SysUserRole queryUserRole = new SysUserRole();
        queryUserRole.setUserId(sysUser.getId());
        List<SysUserRole> userRoles = sysUserRoleDao.select(queryUserRole);
        List<SysRole> sysRoles = sysRoleDao.selectByIds(StringUtils.join(userRoles.stream().map(ele -> ele.getRoleId()).collect(Collectors.toList()), ","));

        List<LhitSecurityRole> roles = sysRoles.stream().map(ele -> new LhitSecurityRole(ele.getKey())).collect(Collectors.toList());

        List<LhitSecurityPermission> perms = sysResList.stream().map(ele -> LhitSecurityPermission.builder().permsCode(ele.getPerms()).url(ele.getUrl()).build()).collect(Collectors.toList());
        LhitSecurityUserPerms userPerms = new LhitSecurityUserPerms();
        userPerms.setRoles(roles);
        userPerms.setPermissions(perms);
        userPerms.setUser(sysUser);
        userPerms.setUserId(String.valueOf(sysUser.getId()));
        return userPerms;

    }
}
