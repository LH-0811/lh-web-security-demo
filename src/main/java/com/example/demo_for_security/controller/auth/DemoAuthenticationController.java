package com.example.demo_for_security.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.example.demo_for_security.adapter.customer_login.oauth2.Oauth2Verification;
import com.example.demo_for_security.adapter.customer_login.phone_code.PhoneUserVerification;
import com.example.demo_for_security.adapter.customer_login.username_pwd.UsernamePasswordUserVerification;
import com.lhit.starter.security.defense.adapter.LhitSecurityTokenManagerAdapter;
import com.lhit.starter.security.defense.adapter.LhitSecurityUserAuthenticationLoginAdapter;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityRole;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityUserPerms;
import com.lhit.starter.security.defense.pojo.user.LhitSecurityUser;
import com.lhit.starter.security.defense.pojo.verification.DefaultUsernamePasswordUserVerification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoAuthenticationController {

    @Autowired
    private LhitSecurityUserAuthenticationLoginAdapter userAuthenticationLoginAdapter;

    @Autowired
    private LhitSecurityTokenManagerAdapter<LhitSecurityUser, LhitSecurityRole> lhitSecurityTokenManagerAdapter;

    @GetMapping("/default/login/{username}/{password}")
    public String defaultLogin(@PathVariable String username, @PathVariable String password) throws Exception {
        DefaultUsernamePasswordUserVerification verification = new DefaultUsernamePasswordUserVerification(username, password);
        String token = userAuthenticationLoginAdapter.userAuthenticationLogin(verification);
        return "defaultLogin登录成功,token:" + token;
    }


    @GetMapping("/custom/login/{username}/{password}")
    public String customLogin(@PathVariable String username, @PathVariable String password) throws Exception {
        UsernamePasswordUserVerification verification = new UsernamePasswordUserVerification(username, password);
        String token = userAuthenticationLoginAdapter.userAuthenticationLogin(verification);
        return "customLogin登录成功,token:" + token;
    }

    @PostMapping("/${lhit.security.oauth2.server.authentication_login_process_path}")
    public String oauth2Login(Oauth2Verification oauth2Verification) throws Exception {
        String token = userAuthenticationLoginAdapter.userAuthenticationLogin(oauth2Verification);
        return "customLogin登录成功,token:" + token;
    }

    @GetMapping("/phone/login/{phone}/{code}")
    public String phoneLogin(@PathVariable String phone, @PathVariable String code) throws Exception {
        PhoneUserVerification phoneUserVerification = PhoneUserVerification.builder().phone(phone).code(code).build();
        String token = userAuthenticationLoginAdapter.userAuthenticationLogin(phoneUserVerification);
        return "phoneLogin登录成功,token:" + token;
    }



    @GetMapping("/info")
    public String userInfo(@RequestHeader(value = "LH_TOKEN", defaultValue = "") String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return "用户token不能为空";
        }
        LhitSecurityUserPerms<LhitSecurityRole, LhitSecurityUser> permsByToken = lhitSecurityTokenManagerAdapter.getPermsByToken(token);
        LhitSecurityUser userInfoByToken = lhitSecurityTokenManagerAdapter.getUserInfoByToken(token);
        Map<String, Object> map = new HashMap<>();
        map.put("perms", permsByToken);
        map.put("userInfo", userInfoByToken);
        return JSONObject.toJSONString(map);
    }


}
