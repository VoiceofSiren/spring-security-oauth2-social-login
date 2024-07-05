package com.example.springsecurityoauth2sociallogin.controller;

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
    public String index(Model model, Authentication authentication,  @AuthenticationPrincipal OAuth2User oAuth2User){

        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;

        if(authenticationToken != null){
            // [1] 사용자의 속성을 가져옴.
            Map<String, Object> attributes = oAuth2User.getAttributes();
            String userName = (String) attributes.get("name");

            // [2] 네이버의 경우에는 response 층 아래에 name 키가 있음.
            if(authenticationToken.getAuthorizedClientRegistrationId().equals("naver")){
                Map<String, Object> response = (Map)attributes.get("response");
                userName = (String)response.get("name");
            }
            // [3] mode에 username을 추가.
            model.addAttribute("user", userName);
        }
        return "index";
    }

}
