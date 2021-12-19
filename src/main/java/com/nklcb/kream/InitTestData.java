package com.nklcb.kream;


import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.security.Role;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.repository.BoardRepository;
import com.nklcb.kream.repository.RoleRepository;
import com.nklcb.kream.repository.UserRepository;
import com.nklcb.kream.service.UserService;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitTestData {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;




    @PostConstruct
    public void dataInit() {
        for (int i = 0; i < 100; i++) {
            Board board = new Board("title" + i, "content" +i);
            boardRepository.save(board);
        }
        Role role = new Role("ADMIN");
        Role role2 = new Role("USER");
        roleRepository.save(role);
        roleRepository.save(role2);
        User user = new User("123", "123", true);
        userService.save(user);

    }
}
