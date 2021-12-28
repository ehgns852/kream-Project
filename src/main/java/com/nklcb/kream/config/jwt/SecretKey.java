package com.nklcb.kream.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SecretKey {



    private String secretKey;



    public SecretKey(@Value("${spring.jwt.secret}") String secretKey) {
        this.secretKey = secretKey;

    }





}
