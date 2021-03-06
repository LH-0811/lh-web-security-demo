package com.example.demo_for_security.adapter.customer_login.username_pwd;

import com.lhit.starter.security.defense.pojo.verification.LhitSecurityUserVerification;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsernamePasswordUserVerification implements LhitSecurityUserVerification {


    // 用户名
    private String username;

    // 密码
    private String password;


}
