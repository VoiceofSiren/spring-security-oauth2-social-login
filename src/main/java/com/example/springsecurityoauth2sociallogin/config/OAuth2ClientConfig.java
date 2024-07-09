package com.example.springsecurityoauth2sociallogin.config;

import com.example.springsecurityoauth2sociallogin.CustomAuthoritiesMapper;
import com.example.springsecurityoauth2sociallogin.service.CustomOAuth2UserService;
import com.example.springsecurityoauth2sociallogin.service.CustomOidcUserService;
import com.example.springsecurityoauth2sociallogin.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ClientConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/static/js/**", "/static/images/**", "/static/css/**", "/static/scss/**").permitAll()
                        .requestMatchers("/api/user").hasAnyRole("SCOPE_profile", "SCOPE_profile_image", "SCOPE_email")
                        .requestMatchers("/api/oidc").hasAnyRole("SCOPE_openid")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                // OAuth 2
                                .userService(customOAuth2UserService)
                                // OpenID Connect
                                .oidcUserService(customOidcUserService))
                )
                /*
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                */
                .userDetailsService(customUserDetailsService)
                .exceptionHandling(except -> except
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
        ;

        return http.build();
    }

}
