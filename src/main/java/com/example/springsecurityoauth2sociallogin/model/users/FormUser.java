package com.example.springsecurityoauth2sociallogin.model.users;

import com.example.springsecurityoauth2sociallogin.model.ProviderUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class FormUser implements ProviderUser {

    private String registrationId;
    private String id;
    private String username;
    private String password;
    private String email;
    private boolean isCertificated;
    private String provider;
    private List<? extends GrantedAuthority> authorities;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPicture() {
        return null;
    }

    @Override
    public String getProvider() {
        return this.provider;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public OAuth2User getOAuth2User() {
        return null;
    }

    @Override
    public boolean isCertificated() {
        return isCertificated;
    }

    @Override
    public void isCertificated(boolean isCertificated) {
        this.isCertificated = isCertificated;
    }
}
