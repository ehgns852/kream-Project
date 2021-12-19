package com.nklcb.kream.entity.security;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "user_role")
@Getter
@NoArgsConstructor
@Slf4j
@ToString(of = {"id","user","role"})
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

    /**
     *정적 팩토리 메서드
     */
    public static UserRole addUserRole(User user, Role role) {
        UserRole userRole = new UserRole(user,role);
        log.info("userRole = {}", userRole);

        return userRole;
    }
}
