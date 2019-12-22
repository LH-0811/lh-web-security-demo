package com.example.demo_for_security.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.example.demo_for_security.adapter.UsernamePasswordUserVerification;
import com.lhit.starter.security.adapter.LhitSecurityTokenManagerAdapter;
import com.lhit.starter.security.adapter.LhitSecurityUserAuthenticationLoginAdapter;
import com.lhit.starter.security.pojo.entity.LhitSecurityRole;
import com.lhit.starter.security.pojo.entity.LhitSecurityUserPerms;
import com.lhit.starter.security.pojo.user.LhitSecurityUser;
import com.lhit.starter.security.pojo.verification.DefaultUsernamePasswordUserVerification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

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
