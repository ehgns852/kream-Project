package com.nklcb.kream.entity.security;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "role_id")
    private Long id;

    private String name;

    public static final Role ADMIN = new Role("ROLE_ADMIN");
    public static final Role USER = new Role("ROLE_USER");

    public Role(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles = new ArrayList<>();


}
