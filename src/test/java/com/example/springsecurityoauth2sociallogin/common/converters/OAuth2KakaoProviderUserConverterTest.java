package com.example.springsecurityoauth2sociallogin.common.converters;

import com.example.springsecurityoauth2sociallogin.common.enums.OAuth2Config;
import com.example.springsecurityoauth2sociallogin.common.util.OAuth2Utils;
import com.example.springsecurityoauth2sociallogin.model.ProviderUser;
import com.example.springsecurityoauth2sociallogin.model.social.KakaoUser;
import org.junit.jupiter.api.Test;

class OAuth2KakaoProviderUserConverterTest implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    @Test
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
            return null;
        }

        return new KakaoUser(OAuth2Utils.getOtherAttributes(
                providerUserRequest.oAuth2User(), "kakao_account", "profile"),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}