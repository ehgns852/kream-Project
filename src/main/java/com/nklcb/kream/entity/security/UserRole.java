package com.nklcb.kream.entity.security;


import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "user_role")
@Getter
public class UserRole {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_role_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public void setUser(User user) {
        this.user = user;
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
