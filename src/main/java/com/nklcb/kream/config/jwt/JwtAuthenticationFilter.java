package com.nklcb.kream.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nklcb.kream.config.auth.PrincipalDetails;
import com.nklcb.kream.entity.security.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


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

        //1. username, password 받아서

        try {
//            BufferedReader br = request.getReader();
//
//            String input = null;
//            while ((input = br.readLine()) != null) {
//                log.info("input = {}",input);
//            }
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);

            log.info("user = {}", user);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            /**
             *  PrincipalDetailsService loadUserByUsername실행
             *  authentication에는 로그인 한 정보가 담김
             */
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

            /**
             * authentication 객체가 session 영역에 저장됨 => 로그인이 되었다는 뜻
             */
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            log.info("getUsername = {}",principalDetails.getUser().getUsername());

            /**
             * authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return을 해 주는것
             * 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는거다.
             * 굳이 jwt 토큰을 사용하면서 세션을 만들 이유가 없음 근데 단지 권한 처리때문에 session에 넣어주는 것이다.
             */


            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     *  attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication함수가 실행됨
     *  jwt 토큰을 만들어서 request요청한 사용자에게 jwt토큰을 response해주면 됨
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication 함수 실행");
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
