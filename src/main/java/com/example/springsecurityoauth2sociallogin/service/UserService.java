package com.example.springsecurityoauth2sociallogin.service;

import com.example.springsecurityoauth2sociallogin.model.ProviderUser;
import com.example.springsecurityoauth2sociallogin.model.users.User;
import com.example.springsecurityoauth2sociallogin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(String registrationId, ProviderUser providerUser) {

        User user = User.builder()
                .registrationId(registrationId)
                .id(providerUser.getId())
                .username(providerUser.getUsername())
                .provider(providerUser.getProvider())
                .email(providerUser.getEmail())
                .authorities(providerUser.getAuthorities())
                .build();

        userRepository.register(user);
    }
}
