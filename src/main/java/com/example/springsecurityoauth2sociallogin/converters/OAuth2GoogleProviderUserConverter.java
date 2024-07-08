package com.example.springsecurityoauth2sociallogin.converters;

import com.example.springsecurityoauth2sociallogin.enums.OAuth2Config;
import com.example.springsecurityoauth2sociallogin.model.ProviderUser;
import com.example.springsecurityoauth2sociallogin.model.social.GoogleUser;
import com.example.springsecurityoauth2sociallogin.util.OAuth2Utils;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        if (providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())) {
            return null;
        }

        return new GoogleUser(OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
                providerUserRequest.oAuth2User(),
                providerUserRequest.clientRegistration());
    }
}