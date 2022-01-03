package com.nklcb.kream.controller.api;

import com.nklcb.kream.dto.UserDto;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.form.UserForm;
import com.nklcb.kream.repository.UserRepository;
import com.nklcb.kream.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public UserDto newUser(@Valid @RequestBody UserDto user) {

        User createUser = User.createApiUser(user.getUsername(), user.getPassword(), user.getEmail());
        userService.adminJoin(createUser);
        UserDto userDto = new UserDto(createUser);

        return userDto;
    }


    /**
     * 회원 단건 조회
     */
    @GetMapping("user/{id}")
    public UserDto one(@PathVariable Long id) throws Exception {
        User user = userService.findById(id);

        UserDto userOne = UserDto.getUserOne(user);

        return userOne;

    }

    /**
     * 회원 전체 조회
     */
    @GetMapping("users/all")
    public Result findAll() {
        List<UserDto> findUsers = userService.findAll();

        return new Result(findUsers.size(), findUsers);
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
