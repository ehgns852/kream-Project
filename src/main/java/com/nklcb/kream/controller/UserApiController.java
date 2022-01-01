package com.nklcb.kream.controller;

import com.nklcb.kream.UserDto;
import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.form.UserForm;
import com.nklcb.kream.repository.BoardRepository;
import com.nklcb.kream.repository.UserRepository;
import com.nklcb.kream.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserApiController {

    private final UserRepository userRepository;
    private final UserService userService;


    /**
     * User, Board 엔티티를 fetch join 으로 전체 조회후 반환
     */
    @GetMapping("/users")
    public Result all() {
        List<UserForm> userAndBoard = userService.findUserAndBoard();
        log.info("userService - all");

        return new Result(userAndBoard.size(),userAndBoard);



    }

    /**
     * User ROLE_ADMIN 권한으로 회원가입
     * username, password, email 입력하면 ->  + id, createDate 반환
     */
    @PostMapping("/user/save")
    public UserDto newUser(@RequestBody UserDto user) {

        User createUser = User.createApiUser(user.getUsername(), user.getPassword(), user.getEmail());
        userService.adminJoin(createUser);
        UserDto userDto = new UserDto(createUser);

        return userDto;
    }



    @GetMapping("users/{id}")
    public User one(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }


    /**
     * Json으로 반환 시 [] -> {}
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    static class Result<T>{
        private int count;
        private T data;

        public Result(T data) {
            this.data = data;
        }
    }


}
