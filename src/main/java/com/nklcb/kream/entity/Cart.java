package com.nklcb.kream.entity;

import com.nklcb.kream.entity.audit.AuditListener;
import com.nklcb.kream.entity.audit.Auditable;
import com.nklcb.kream.entity.embedded.TimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditListener.class)
public class Cart implements Auditable {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @Embedded
    private TimeEntity timeEntity;

    @OneToMany(mappedBy = "cart")
    private List<ItemCart> itemCarts = new ArrayList<>();



    /**
     * @PrePersist TimeEntity
     */
    @Override
    public void setTimeEntity(TimeEntity timeEntity) {
        this.timeEntity = timeEntity;
    }
}
