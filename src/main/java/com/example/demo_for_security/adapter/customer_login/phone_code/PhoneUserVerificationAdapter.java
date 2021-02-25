package com.example.demo_for_security.adapter.customer_login.phone_code;

import com.google.common.collect.Lists;
import com.lhit.starter.security.defense.adapter.LhitSecurityUserVerificationAdapter;
import com.lhit.starter.security.defense.annotation.LhitUserVerification;
import com.lhit.starter.security.defense.exception.UserVerificationException;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityPermission;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityRole;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityUserPerms;
import com.lhit.starter.security.defense.pojo.user.DefaultLhitSecurityUser;

/**
 * 用户认证适配器
 * <p>
 * 通过该适配器 指定用户的登录认证过程 并将 用户的权限获取到
 */
@LhitUserVerification
public class PhoneUserVerificationAdapter implements LhitSecurityUserVerificationAdapter<PhoneUserVerification> {


    @Override
    public LhitSecurityUserPerms verification(PhoneUserVerification verification) throws Exception {
        if (!"17600000001".equals(verification.getPhone())) {
            throw UserVerificationException.create(verification.getPhone(),"手机号错误，默认手机号17600000001");
        } else if (!"123456".equals(verification.getCode())) {
            throw UserVerificationException.create(verification.getCode(),"验证码不正确：默认密码123456");
        } else {
            LhitSecurityRole role = new LhitSecurityRole("admin");
            LhitSecurityPermission permission = new LhitSecurityPermission("/**", "all");
            DefaultLhitSecurityUser user = DefaultLhitSecurityUser.builder().userId("default").password("user").username("user").build();
            return new LhitSecurityUserPerms(Lists.newArrayList(new LhitSecurityRole[]{role}), Lists.newArrayList(new LhitSecurityPermission[]{permission}), user.getUserId(), user);
        }

    }
}
