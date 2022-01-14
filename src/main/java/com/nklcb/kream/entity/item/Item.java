package com.nklcb.kream.entity.item;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.*;
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


    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_file_id")
    private UploadFile attachFile;

//    @OneToMany
//    @JoinColumn(name = "upload_file_id")
//    private List<UploadFile> imageFiles;




    protected Item(String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createDate = createDate;
    }


    @Builder
    public Item(String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate, UploadFile attachFile) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createDate = createDate;
        this.attachFile = attachFile;
    }



    /**
     * item 생성 팩토리 메서드
     */
    public static Item addItem(String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate) {
        return new Item(brandName, itemName, price, stockQuantity, createDate);
    }
}
