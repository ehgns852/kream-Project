package com.nklcb.kream.config.auth;

import com.nklcb.kream.entity.security.Role;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.entity.security.UserRole;
import com.nklcb.kream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.nklcb.kream.entity.security.Role.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;



    //구글로 부터 받은 userRequest 데이터에 대한 후처리 되는 함수
   @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       log.info("getClientRegistration = {}", userRequest.getClientRegistration());// 어떤 OAuth로 로그인 하였는지 확인가능
       log.info("getAccessToken = {}", userRequest.getAccessToken().getTokenValue());
       /**
        * 구글 로그인 버튼 클릭 -> 구글로그인창 -> 로그인 완료 -> code를 리턴(OAuth-client 라이브러리) ->AccessToken 요청
        * userRequest 정보 -> 회원프로필 받아야함(loadUser 함수) -> 구글로부터 회원 프로필을 받아준다.
        */
       OAuth2User oAuth2User = super.loadUser(userRequest);
       log.info("getAttributes = {}", oAuth2User.getAttributes());

       String provider = userRequest.getClientRegistration().getClientId(); //google
       String providerId = oAuth2User.getAttribute("sub"); //google primary Key
       String username = provider + "_" + providerId;
       String password = passwordEncoder.encode("겟인데어");
       String email = oAuth2User.getAttribute("email");
       UserRole userRole = UserRole.addRole(USER);

       User userEntity = userRepository.findByUsername(username);
       log.info("username = {}", userEntity);
       if (userEntity == null) {
            userEntity = User.builder()
                   .username(username)
                   .password(password)
                   .email(email)
                   .provider(provider)
                   .providerId(providerId)
                   .userRole(userRole)
                   .build();

            log.info("PrincipalOauth2 userEntity = {]", userEntity);

           userRepository.save(userEntity);

           log.info("Oauth2 Join = {}", userEntity);
       } else {
           return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
       }


       return super.loadUser(userRequest);
    }
}
