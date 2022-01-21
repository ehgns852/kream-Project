package com.nklcb.kream.entity;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@ToString(of = {"id", "username", "password", "enabled","email","provider","providerId","createDate"})
@Slf4j
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    private String email;

    private String provider;
    private String providerId;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "user",cascade = ALL)
    private List<UserRole> userRoles = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Board> boards = new ArrayList<>();


    public static User createApiUser(String username, String password, String email) {
         return new User(username,password,email);
    }


    public void signUp(String username, String password, UserRole userRole, boolean enabled, LocalDateTime localDateTime, String email) {
        this.username = username;
        this.password = password;
        this.userRoles.add(userRole);
        userRole.setUser(this);
        this.enabled = enabled;
        this.createDate = localDateTime;
        this.email = email;


        log.info("User.signup ={}", this);


    }

    public User(String username, String password, boolean enabled, String email, LocalDateTime createDate) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Builder
    public User(String username, String password, String email, String provider,
                String providerId, UserRole userRole, LocalDateTime createDate, boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.userRoles.add(userRole);
        userRole.setUser(this);
        this.createDate = createDate;
        this.enabled = enabled;
    }


}
