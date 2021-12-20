package com.nklcb.kream.service;

import com.nklcb.kream.UserDto;
import com.nklcb.kream.entity.security.Role;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.entity.security.UserRole;
import com.nklcb.kream.repository.RoleRepository;
import com.nklcb.kream.repository.UserRepository;
import com.nklcb.kream.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nklcb.kream.entity.security.Role.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void save(User user) {
        log.info("USER SERVICE IN");
        //클라이언트에서 회원 가입시 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        log.info("encoder success");

        UserRole userRole = UserRole.addUserRole(user, USER);
        log.info("save userRole = {}", userRole);

        user.signUp(user.getUsername(), encodedPassword, true, userRole);
        log.info("signup");


        log.info("Before Save user = {}", user);
        userRepository.save(user);
        log.info("After Save user = {}", user);
    }



}
