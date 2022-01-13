package com.nklcb.kream.entity.item;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"id","brandName","itemName","price","stockQuantity","createDate"})
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String brandName;

    private String itemName;

    private int price;

    private int stockQuantity;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String filePath;




    protected Item(String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createDate = createDate;
    }


    @Builder
    public Item(String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate, String filePath) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createDate = createDate;
        this.filePath = filePath;
    }



    /**
     * item 생성 팩토리 메서드
     */
    public static Item addItem(String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate) {
        return new Item(brandName, itemName, price, stockQuantity, createDate);
    }
}
