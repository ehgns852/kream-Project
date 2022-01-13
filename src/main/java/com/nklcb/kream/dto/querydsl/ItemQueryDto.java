package com.nklcb.kream.dto.querydsl;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemQueryDto {

    private Long id;

    private String brandName;

    private String itemName;

    private int price;

    private int stockQuantity;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private MultipartFile file;


    @QueryProjection
    public ItemQueryDto(Long id, String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate) {
        this.id = id;
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createDate = createDate;
    }

}