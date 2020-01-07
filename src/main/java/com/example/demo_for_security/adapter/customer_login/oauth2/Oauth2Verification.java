package com.example.demo_for_security.adapter.customer_login.oauth2;

import com.lhit.starter.security.oauth2.pojo.LhitSecurityUserOauthVerification;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Oauth2Verification implements LhitSecurityUserOauthVerification {

    private String username;

    private String password;

    private String code;

}
