package com.nklcb.kream.entity.security;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserRole> userRoles = new ArrayList<>();




    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }




    public void signUp(String username, String password, boolean enabled, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRoles.add(userRole);
        userRole.setUser(this);

        log.info("User.signup ={}", this);


    }

    @Builder
    public User(String username, String password, String email, String provider, String providerId, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.userRoles.add(userRole);
        userRole.setUser(this);
    }

}
