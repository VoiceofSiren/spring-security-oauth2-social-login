package com.example.springsecurityoauth2sociallogin.service;

import com.example.springsecurityoauth2sociallogin.common.converters.ProviderUserRequest;
import com.example.springsecurityoauth2sociallogin.model.PrincipalUser;
import com.example.springsecurityoauth2sociallogin.model.ProviderUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oAuth2User);

        // 반환하고자 하는 계정의 타입을 지정
        ProviderUser providerUser = providerUser(providerUserRequest);

        // 본인인증 체크
        // 기본은 본인인증을 하지 않은 상태임
        selfCertificate(providerUser);

        // 회원 가입
        super.register(providerUser, userRequest);

        return new PrincipalUser(providerUser);
    }
}
