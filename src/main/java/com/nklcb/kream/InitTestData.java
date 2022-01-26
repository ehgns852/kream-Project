package com.nklcb.kream;


import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.User;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.repository.RoleRepository;
import com.nklcb.kream.service.BoardService;
import com.nklcb.kream.service.ItemService;
import com.nklcb.kream.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

import static com.nklcb.kream.entity.Role.ADMIN;
import static com.nklcb.kream.entity.Role.USER;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitTestData {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final BoardService boardService;
    private final ItemService itemService;




    @PostConstruct
    @Transactional
    public void dataInit() {



        roleRepository.save(ADMIN);
        roleRepository.save(USER);

        User user1 = User.createApiUser("123", "123", "ehgns");
        User user2 = User.createApiUser("321", "321", "dobi@naver.com");


        userService.adminJoin(user1);
        userService.userJoin(user2);

        for (int i = 0; i < 20; i++) {
            Board board = Board.createBoard("title" + i, "content" + i);
            if (i % 2 == 0) {
                boardService.save("321", board);
            } else {
                boardService.save("123", board);
            }
        }

        for (int i = 0; i < 20; i++) {
            Item item = Item.builder()
                    .brandName("nike" + i).itemName("air max" + i)
                    .price(20000)
                    .stockQuantity(100)
                    .attachFile(null)
                    .build();

            itemService.save(item);


        }


    }


    }


