package com.nklcb.kream;


import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.entity.security.UserRole;
import com.nklcb.kream.repository.BoardRepository;
import com.nklcb.kream.repository.RoleRepository;
import com.nklcb.kream.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.time.LocalDateTime;

import static com.nklcb.kream.entity.security.Role.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitTestData {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;

//
//
//
    @PostConstruct
    public void dataInit() {
//        for (int i = 0; i < 100; i++) {
//            Board board = new Board("title" + i, "content" +i);
//            boardRepository.save(board);
//        }
        LocalDateTime createDate = LocalDateTime.now();


        roleRepository.save(ADMIN);
        roleRepository.save(USER);

        User user1 = new User("123", "123", true, "dobi", createDate);
        User user2 = new User("321", "321", true, "ehgns", createDate);


        userService.adminJoin(user1);
        userService.userJoin(user2);

    }


    }

