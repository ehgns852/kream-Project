package com.nklcb.kream;


import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.User;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.repository.ItemRepository;
import com.nklcb.kream.repository.RoleRepository;
import com.nklcb.kream.service.BoardService;
import com.nklcb.kream.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import java.time.LocalDateTime;

import static com.nklcb.kream.entity.Role.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitTestData {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final BoardService boardService;
    private final ItemRepository itemRepository;




    @PostConstruct
    @Transactional
    public void dataInit() {



        roleRepository.save(ADMIN);
        roleRepository.save(USER);

        User user1 = new User("123", "123", true, "dobi", LocalDateTime.now());
        User user2 = new User("321", "321", true, "ehgns", LocalDateTime.now());


        userService.adminJoin(user1);
        userService.userJoin(user2);

        for (int i = 0; i < 20; i++) {
            Board board = Board.createBoard("title" + i, "content" + i, LocalDateTime.now());
            if (i % 2 == 0) {
                boardService.save("321", board);
            } else {
                boardService.save("123", board);
            }
        }

        for (int i = 0; i < 20; i++) {
            Item item = Item.addItem("nike" + i, "air max" + i, 200000, 100, LocalDateTime.now());

            itemRepository.save(item);
        }


    }


    }

