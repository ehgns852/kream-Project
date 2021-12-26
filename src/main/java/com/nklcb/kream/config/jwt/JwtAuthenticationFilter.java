package com.nklcb.kream.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음
 * login 요청해서 username, password 전송하면 (Post)
 * 이 필터가 동작
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    /**
     * login 요청을 하면 로그인 시도를 위해서 실행되는 함수
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("AuthenticationFilter 로그인 시도중");

        return super.attemptAuthentication(request, response);
    }
}