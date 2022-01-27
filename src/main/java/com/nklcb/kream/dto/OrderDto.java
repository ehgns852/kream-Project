package com.nklcb.kream.dto;

import com.nklcb.kream.entity.OrderItem;
import com.nklcb.kream.entity.OrderStatus;
import com.nklcb.kream.entity.User;
import com.nklcb.kream.entity.embedded.TimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {

    private Long id;

    @Range(min = 1, max = 10, message = "1개 이상 10개 이하로 주문이 가능합니다.")
    private int quantity;

    @Embedded
    private TimeEntity timeEntity;


    private OrderStatus orderStatus;
}
