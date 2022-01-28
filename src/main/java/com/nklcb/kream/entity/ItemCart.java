package com.nklcb.kream.entity;

import com.nklcb.kream.entity.item.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCart {

    @Id
    @GeneratedValue
    @Column(name = "item_cart_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
