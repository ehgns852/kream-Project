package com.nklcb.kream.service;

import com.nklcb.kream.entity.security.Role;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.entity.security.UserRole;
import com.nklcb.kream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User save(User user) {

        //클라이언트에서 회원 가입시 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.signUp(user.getUsername(), encodedPassword, true,);



        return userRepository.save(user);
    }



}
