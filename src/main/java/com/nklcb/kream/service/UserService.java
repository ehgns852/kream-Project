package com.nklcb.kream.service;

import com.nklcb.kream.UserDto;
import com.nklcb.kream.entity.security.Role;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.entity.security.UserRole;
import com.nklcb.kream.form.UserForm;
import com.nklcb.kream.repository.RoleRepository;
import com.nklcb.kream.repository.UserRepository;
import com.nklcb.kream.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nklcb.kream.entity.security.Role.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    /**
     * User 회원가입
     */
    @Transactional
    public void userJoin(User user) {
        log.info("USER SERVICE IN");
        //클라이언트에서 회원 가입시 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        log.info("encoder success");

        UserRole userRole = UserRole.addUserRole(user, USER);
        log.info("save userRole = {}", userRole);
        LocalDateTime createDate = LocalDateTime.now();

        user.signUp(user.getUsername(), encodedPassword, userRole, true, createDate, user.getEmail());
        log.info("signup");


        log.info("Before Save user = {}", user);
        userRepository.save(user);
        log.info("After Save user = {}", user);
    }


    /**
     * admin 회원가입
     * 추후에 분리 예정
     */
    @Transactional
    public void adminJoin(User admin) {

        String encodedPassword = passwordEncoder.encode(admin.getPassword());

        UserRole userRole = UserRole.addUserRole(admin, ADMIN);

        LocalDateTime createDate = LocalDateTime.now();

        admin.signUp(admin.getUsername(), encodedPassword, userRole,true, createDate, admin.getEmail());

        userRepository.save(admin);




    }

    @Transactional(readOnly = true)
    public List<UserForm> findUserAndBoard() {

        //User, Board fetch join
        List<User> findUser = userRepository.findAllByWithBoard();

        log.info("findAllByWithBoard");

        //User 리스트를 DTO로 변환후 리스트로 반환
        List<UserForm> collect = findUser.stream()
                .map(user -> new UserForm(user))
                .collect(Collectors.toList());

        return collect;


    }

}
