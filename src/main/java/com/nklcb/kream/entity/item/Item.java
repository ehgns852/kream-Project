package com.nklcb.kream.entity.item;

import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    /**
     * Item + UploadFile 수정시 사용
     * 기존 파일이 없다면 -> 새로 생성하여 저장
     */
    public void updateItem(ItemQueryDto item) {
        this.brandName = item.getBrandName();
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        this.updateDate = LocalDateTime.now();
        if (this.attachFile == null) {
            this.attachFile = new UploadFile(item.getUploadFileName(), item.getStoreFileName(), item.getFilePath());
        } else {
            getAttachFile().updateUploadFile(item.getUploadFileName(), item.getStoreFileName(), item.getFilePath());
        }

    }

    /**
     * uploadFile 변경x  Only Item Entity 수정시 사용
     */
    public void updateOnlyItem(ItemQueryDto itemOnly) {
        this.brandName = itemOnly.getBrandName();
        this.itemName = itemOnly.getItemName();
        this.price = itemOnly.getPrice();
        this.stockQuantity = itemOnly.getStockQuantity();
    }
}
