package com.nklcb.kream.entity;


import com.nklcb.kream.entity.embedded.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;


}
