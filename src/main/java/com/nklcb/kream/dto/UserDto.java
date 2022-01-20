package com.nklcb.kream.dto;


import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class UserDto {

    private Long id;

    @NotNull
    @Length(min = 3, max = 20)
    private String username;

    @NotNull
    @Length(min = 3, max = 20)
    private String password;
    private String email;
    private LocalDateTime createUserDate;

    private boolean enabled;

    private String provider;
    private String providerId;

    private List<BoardDto> boards;




    protected UserDto(String username,String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static UserDto createUser(User user){
        return new UserDto(user.getUsername(), user.getPassword(), user.getEmail());
    }



    /**
     * 컬렉션 객체인 Board -> BoardForm으로 변환
     */
    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.email = user.getEmail();
        this.createUserDate = user.getCreateDate();
        this.boards = user.getBoards()
                .stream()
                .map(board -> new BoardDto(board))
                .collect(Collectors.toList());

        log.info("UserFormConstructor in = {}", this);
    }









    public static UserDto  getUserOne(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .createUserDate(user.getCreateDate())
                .enabled(user.isEnabled())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .build();

    }


}
