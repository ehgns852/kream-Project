package com.nklcb.kream.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class ItemDto {

    private Long id;

    private String brandName;

    private String itemName;

    private int price;

    private int stockQuantity;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createDate;

    private LocalDateTime updateDate;


    @QueryProjection
    public ItemDto(Long id, String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate) {
        this.id = id;
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createDate = createDate;
    }
}
