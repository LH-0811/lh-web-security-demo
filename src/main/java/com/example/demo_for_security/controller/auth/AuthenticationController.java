package com.example.demo_for_security.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.lhit.starter.security.adapter.LhitSecurityTokenManagerAdapter;
import com.lhit.starter.security.adapter.LhitSecurityUserAuthenticationLoginAdapter;
import com.lhit.starter.security.pojo.LhitSecurityRole;
import com.lhit.starter.security.pojo.LhitSecurityUser;
import com.lhit.starter.security.pojo.LhitSecurityUserPerms;
import com.lhit.starter.security.pojo.UsernamePasswordUserVerification;
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

    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password) throws Exception {
        UsernamePasswordUserVerification verification = new UsernamePasswordUserVerification(username, password);
        String token = userAuthenticationLoginAdapter.userAuthenticationLogin(verification);
        return "登录成功,token:" + token;
    }

    @GetMapping("/info")
    public String userInfo(@RequestHeader(value = "LH_TOKEN", defaultValue = "") String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return "用户token不能为空";
        }
        LhitSecurityUserPerms<LhitSecurityRole> permsByToken = lhitSecurityTokenManagerAdapter.getPermsByToken(token);
        LhitSecurityUser userInfoByToken = lhitSecurityTokenManagerAdapter.getUserInfoByToken(token);
        Map<String, Object> map = new HashMap<>();
        map.put("perms", permsByToken);
        map.put("userInfo", userInfoByToken);
        return JSONObject.toJSONString(map);
    }


}
