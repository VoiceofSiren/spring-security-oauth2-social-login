package com.example.springsecurityoauth2sociallogin.common.converters;

import com.example.springsecurityoauth2sociallogin.model.ProviderUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class DelegatingProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{

    private List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> converters;

    public DelegatingProviderUserConverter() {
        List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> providerUserConverters =
                Arrays.asList(new UserDetailsProviderUserConverter(),
                              new OAuth2GoogleProviderUserConverter(),
                              new OAuth2NaverProviderUserConverter(),
                              new OAuth2KakaoProviderUserConverter());

        this.converters = Collections.unmodifiableList(new LinkedList<>(providerUserConverters));
    }
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        Assert.notNull(providerUserRequest, "providerUserRequest cannot be null.");

        for (ProviderUserConverter<ProviderUserRequest, ProviderUser> converter: converters) {
            ProviderUser providerUser = converter.converter(providerUserRequest);

            if (providerUser != null) {
                return providerUser;
            }
        }

        return null;
    }
}
