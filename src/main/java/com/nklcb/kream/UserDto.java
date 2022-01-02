package com.nklcb.kream;


import com.nklcb.kream.entity.security.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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


    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(User user) {
        this.id  = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.createUserDate = user.getCreateDate();
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
