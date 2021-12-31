package com.nklcb.kream.controller;

import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.form.UserForm;
import com.nklcb.kream.repository.BoardRepository;
import com.nklcb.kream.repository.UserRepository;
import com.nklcb.kream.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserApiController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final BoardRepository boardRepository;


    /**
     * User, Board 엔티티를 fetch join 으로 전체 조회후 반환
     */
    @GetMapping("/users")
    public Result all() {
        List<UserForm> userAndBoard = userService.findUserAndBoard();
        log.info("userService - all");

        return new Result(userAndBoard.size(),userAndBoard);



    }

    @PostMapping("/users")
    public User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("users/{id}")
    public User one(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }


    /**
     * Json으로 반환 시 [] -> {}
     */
    @AllArgsConstructor
    @Getter
    static class Result<T>{
        private int count;
        private T data;


    }


}
