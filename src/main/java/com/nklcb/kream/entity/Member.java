package com.nklcb.kream.entity;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;



}
