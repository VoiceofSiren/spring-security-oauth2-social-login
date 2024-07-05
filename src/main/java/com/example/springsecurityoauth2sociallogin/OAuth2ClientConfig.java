package com.example.springsecurityoauth2sociallogin;

import com.example.springsecurityoauth2sociallogin.service.CustomOAuth2UserService;
import com.example.springsecurityoauth2sociallogin.service.CustomOidcUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ClientConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/static/js/**", "/static/images/**", "/static/css/**", "/static/scss/**").permitAll()
                        .requestMatchers("/api/user").hasAnyRole("SCOPE_profile", "SCOPE_email")
                        .requestMatchers("/api/oidc").hasAnyRole("SCOPE_openid")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)
                                .oidcUserService(customOidcUserService))
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
        ;

        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthoritiesMapper() {
        return new CustomAuthoritiesMapper();
    }
}
