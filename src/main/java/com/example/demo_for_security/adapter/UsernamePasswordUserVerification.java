package com.example.demo_for_security.adapter;

import com.lhit.starter.security.pojo.LhitSecurityUserVerification;
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
