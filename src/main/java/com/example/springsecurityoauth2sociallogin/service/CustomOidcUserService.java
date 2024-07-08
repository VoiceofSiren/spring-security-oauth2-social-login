package com.example.springsecurityoauth2sociallogin.service;

import com.example.springsecurityoauth2sociallogin.converters.ProviderUserRequest;
import com.example.springsecurityoauth2sociallogin.model.ProviderUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();
        OidcUser oidcUser = oidcUserService.loadUser(userRequest);

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,oidcUser);

        // 반환하고자 하는 계정의 타입을 지정
        ProviderUser providerUser = super.providerUser(providerUserRequest);

        // 회원 가입
        super.register(providerUser, userRequest);

        return oidcUser;
    }
}
