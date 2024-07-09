package com.example.springsecurityoauth2sociallogin.common.converters;

public interface ProviderUserConverter<T, R> {

    R converter(T t);
}
