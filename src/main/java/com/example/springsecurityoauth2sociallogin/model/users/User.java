package com.example.springsecurityoauth2sociallogin.model.users;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class User implements Serializable {

    private String registrationId;
    private String id;
//    private String ci;
    private String username;
    private String name;
    private String password;
    private String provider;
    private String email;
    private String picture;
    private List<? extends GrantedAuthority> authorities;

}
