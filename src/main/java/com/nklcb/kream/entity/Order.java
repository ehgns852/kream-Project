package com.nklcb.kream.entity;

import com.nklcb.kream.entity.audit.AuditListener;
import com.nklcb.kream.entity.audit.Auditable;
import com.nklcb.kream.entity.embedded.TimeEntity;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","orderStatus"})
@Table(name = "orders")
@EntityListeners(AuditListener.class)
public class Order implements Auditable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private int purchaseQuantity;


    @Embedded
    private TimeEntity timeEntity;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


    @Enumerated(STRING)
    private OrderStatus orderStatus;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;




    @Builder
    public Order(int purchaseQuantity,User user, List<OrderItem> orderItems, OrderStatus orderStatus, Delivery delivery) {
        this.purchaseQuantity = purchaseQuantity;
        this.user = user;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.delivery = delivery;
    }

    /**
     * 연관관계 편의 메서드
     */
    public void addUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    /**
     * 생성 메서드
     */
    public static Order Order() {
        return new Order();
    }

    /**
     * @PrePersist TimeEntity
     */
    @Override
    public void setTimeEntity(TimeEntity timeEntity) {
        this.timeEntity = timeEntity;
    }


}
