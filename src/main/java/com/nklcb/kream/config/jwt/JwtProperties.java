package com.nklcb.kream.config.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;


@Getter
public class JwtProperties {

    @Value("${jwt.token.secret-key}")
    public static String SecretKey;
}
