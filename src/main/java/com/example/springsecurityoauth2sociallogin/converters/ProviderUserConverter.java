package com.example.springsecurityoauth2sociallogin.converters;

public interface ProviderUserConverter<T, R> {

    R converter(T t);
}
