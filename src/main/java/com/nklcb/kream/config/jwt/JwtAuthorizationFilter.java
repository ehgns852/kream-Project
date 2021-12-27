package com.nklcb.kream.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 시큐리티가 filter 를 가지고 있는데 그 필터중에 BasicAuthenticationFilter 가 있음
 * 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음
 * 만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 타지않음
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    /**
     * 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 됨
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilterInternal(request, response, chain);
        log.info("인증이나 권한이 필요한 주소 요청이 됨");

        String jwtHeader = request.getHeader("Authorization");
        log.info("jwtHeader = {}", jwtHeader);


        /**
         * header가 있는지 확인
         */
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);

            return;
        }

        /**
         * jwt 토큰을 검증을 해서 정상적인 사용자인지 확인
         */
        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");

    }
}
