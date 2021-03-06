package com.nklcb.kream.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "user_role")
@Getter
@NoArgsConstructor(access = PROTECTED)
@Slf4j
@ToString(of = {"id"})
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

    protected void setUser(User user) {
        this.user = user;
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public UserRole(Role role) {
        this.role = role;
    }


    /**
     * 연관관계 편의 메서드
     */
    public void addUserRole(User user){
        this.user = user;
        user.getUserRoles().add(this);
    }



    /**
     *정적 팩토리 메서드
     */
    public static UserRole addUserRole(User user, Role role) {
        UserRole userRole = new UserRole(user,role);

        log.info("userRole = {}", userRole);

        return userRole;
    }

    /**
     *정적 팩토리 메서드
     */
    public static UserRole addRole(Role role) {
        return new UserRole(role);
    }

}
