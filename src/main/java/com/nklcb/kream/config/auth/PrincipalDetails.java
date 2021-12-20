package com.nklcb.kream.config.auth;

import com.nklcb.kream.entity.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
 * 로그인 진행이 완료가 되면 Session을 만들어준다.(security contextHolder)
 * 오브젝트 => Authentication 타입 객체
 * Authentication 안에 User 정보가 있어야 됨
 * User 오브젝트타입 => UserDetails 타입 객체
 *
 * Security Session => Autentication => UserDetails(PrincipalDetails)
 */
@Transactional(readOnly = true)
@Slf4j
public class PrincipalDetails implements UserDetails {

    private User user; //콤포지션
    private List<String> rolesName;

    public PrincipalDetails(User user, List<String> rolesName) {
        this.user = user;
        this.rolesName = rolesName;
        log.info("UserDetails constructor = {}" ,user);

    }


    /**
     * 해당 User의 권한을 리턴하는 곳
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return rolesName.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
