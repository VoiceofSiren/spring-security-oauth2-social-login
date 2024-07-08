package com.example.springsecurityoauth2sociallogin.service;

import com.example.springsecurityoauth2sociallogin.converters.ProviderUserConverter;
import com.example.springsecurityoauth2sociallogin.converters.ProviderUserRequest;
import com.example.springsecurityoauth2sociallogin.model.*;
import com.example.springsecurityoauth2sociallogin.model.social.GoogleUser;
import com.example.springsecurityoauth2sociallogin.model.social.KeycloakUser;
import com.example.springsecurityoauth2sociallogin.model.social.NaverUser;
import com.example.springsecurityoauth2sociallogin.model.users.User;
import com.example.springsecurityoauth2sociallogin.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {

        User user = userRepository.findByUsername(providerUser.getUsername());

        if (user == null) {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            userService.register(registrationId, providerUser);
        } else {
            System.out.println("user = " + user);
        }

    }

    public ProviderUser providerUser(ProviderUserRequest providerUserRequest) {

        return providerUserConverter.converter(providerUserRequest);

    }

}