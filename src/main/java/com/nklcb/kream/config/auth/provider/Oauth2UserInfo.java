package com.nklcb.kream.config.auth.provider;

public interface Oauth2UserInfo {
    String getProviderId();

    String getProvider();
    String getEmail();
    String getName();

}
