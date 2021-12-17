package com.nklcb.kream;


import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.repository.BoardRepository;
import com.nklcb.kream.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitTestData {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;



    @PostConstruct
    public void dataInit() {
        for (int i = 0; i < 100; i++) {
            Board board = new Board("title" + i, "content" +i);
            boardRepository.save(board);
        }
        User user = new User("dobi", "qkqh", true);
        userRepository.save(user);
    }
}
