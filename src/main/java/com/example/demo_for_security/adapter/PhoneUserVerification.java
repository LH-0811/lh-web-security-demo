package com.example.demo_for_security.adapter;

import com.lhit.starter.security.pojo.verification.LhitSecurityUserVerification;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneUserVerification implements LhitSecurityUserVerification {


    // 用户名
    private String phone;

    // 密码
    private String code;


}
