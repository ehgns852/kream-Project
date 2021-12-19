package com.nklcb.kream.entity.security;


import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"id", "username", "password", "enabled"})
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

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<>();

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String password) {
        this.password = password;
    }

    public void signUp(String username, String password, boolean enabled, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRoles.add(userRole);
        userRole.setUser(this);

    }
}
