package com.nklcb.kream;


import com.nklcb.kream.entity.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createUser;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(User user) {
        this.id  = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.createUser = user.getCreateDate();
    }
}
