package com.example.demo_for_security.adapter;

import com.example.demo_for_security.dao.SysRoleDao;
import com.example.demo_for_security.dao.SysUserDao;
import com.example.demo_for_security.dao.SysUserRoleDao;
import com.example.demo_for_security.enum_.ResType;
import com.example.demo_for_security.pojo.SysRes;
import com.example.demo_for_security.pojo.SysRole;
import com.example.demo_for_security.pojo.SysUser;
import com.example.demo_for_security.pojo.SysUserRole;
import com.google.common.collect.Lists;
import com.lhit.starter.security.defense.adapter.LhitSecurityUserVerificationAdapter;
import com.lhit.starter.security.defense.annotation.LhitUserVerification;
import com.lhit.starter.security.defense.exception.UserVerificationException;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityPermission;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityRole;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityUserPerms;
import com.lhit.starter.security.defense.pojo.user.DefaultLhitSecurityUser;
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
public class PhoneUserVerificationAdapter implements LhitSecurityUserVerificationAdapter<PhoneUserVerification> {


    @Override
    public LhitSecurityUserPerms verification(PhoneUserVerification verification) throws Exception {
        if (!"17664059624".equals(verification.getPhone())) {
            throw UserVerificationException.builder().username(verification.getPhone()).msg("手机号错误，默认手机号17664059624").build();
        } else if (!"123456".equals(verification.getCode())) {
            throw UserVerificationException.builder().username(verification.getCode()).msg("验证码不正确：默认密码123456").build();
        } else {
            LhitSecurityRole role = new LhitSecurityRole("admin");
            LhitSecurityPermission permission = new LhitSecurityPermission("/**", "all");
            DefaultLhitSecurityUser user = DefaultLhitSecurityUser.builder().userId("default").password("user").username("user").build();
            return new LhitSecurityUserPerms(Lists.newArrayList(new LhitSecurityRole[]{role}), Lists.newArrayList(new LhitSecurityPermission[]{permission}), user.getUserId(), user);
        }

    }
}
