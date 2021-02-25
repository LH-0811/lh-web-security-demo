package com.example.demo_for_security.adapter.adapter.tokenmgr.jwt;

import com.lhit.starter.security.defense.adapter.LhitSecurityTokenGenerateAdapter;
import com.lhit.starter.security.defense.pojo.entity.LhitSecurityUserPerms;
import com.lhit.starter.security.defense.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtLhitSecurityTokenGenerateAdapter<U, R> implements LhitSecurityTokenGenerateAdapter<U, R> {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String generateToken(LhitSecurityUserPerms<U, R> perms) {
        return jwtUtils.geneJsonWebToken(perms);
    }




}
