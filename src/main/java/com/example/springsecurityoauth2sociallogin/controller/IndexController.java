package com.example.springsecurityoauth2sociallogin.controller;

import com.example.springsecurityoauth2sociallogin.common.util.OAuth2Utils;
import com.example.springsecurityoauth2sociallogin.model.PrincipalUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication, @AuthenticationPrincipal PrincipalUser principalUser){

        String view = "index";

        if (authentication != null){

            String userName;

            // 1) OAuth2 인증일 경우
            if (authentication instanceof OAuth2AuthenticationToken) {
                userName = OAuth2Utils.oAuth2UserName((OAuth2AuthenticationToken) authentication, principalUser);
            }
            // 2) Form 인증일 경우
            else {
                userName = principalUser.providerUser().getUsername();
            }

            model.addAttribute("user", userName);
            model.addAttribute("provider", principalUser.providerUser().getProvider());

            if(!principalUser.providerUser().isCertificated()) view = "selfcert";
        }
        return view;
    }

}
