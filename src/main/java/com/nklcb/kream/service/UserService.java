package com.nklcb.kream.service;

import com.nklcb.kream.entity.security.Role;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.entity.security.UserRole;
import com.nklcb.kream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User save(User user) {



        log.info("USER SERVICE IN");
        //클라이언트에서 회원 가입시 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        log.info("encoder success");

        Role role = new Role("111");
        UserRole userRole = UserRole.addUserRole(user, role);
        user.signUp(user.getUsername(), encodedPassword, true, userRole);

        return userRepository.save(user);
    }



}
