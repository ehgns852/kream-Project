package com.nklcb.kream.entity.item;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.entity.embedded.TimeEntity;
import lombok.*;

import javax.persistence.*;

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

    @Embedded
    private TimeEntity timeEntity;


    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_file_id")
    private UploadFile attachFile;



    protected Item(String brandName, String itemName, int price, int stockQuantity) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    @Builder
    public Item(String brandName, String itemName, int price, int stockQuantity, UploadFile attachFile) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.attachFile = attachFile;
    }





    /**
     * item 생성 팩토리 메서드
     */
    public static Item addItem(String brandName, String itemName, int price, int stockQuantity) {
        return new Item(brandName, itemName, price, stockQuantity);
    }


    /**
     * Item + UploadFile 수정시 사용
     * 기존 파일이 없다면 -> 새로 생성하여 저장
     */
    public void updateItem(ItemDto item, UploadFile uploadFile) {
        this.brandName = item.getBrandName();
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
        if (this.attachFile == null) {
            this.attachFile = new UploadFile(uploadFile.getUploadFileName(), uploadFile.getStoreFileName(), uploadFile.getFilePath());
        } else {
            getAttachFile().updateUploadFile(uploadFile.getUploadFileName(), uploadFile.getStoreFileName(), uploadFile.getFilePath());
        }

    }

    /**
     * uploadFile 변경x  Only Item Entity 수정시 사용
     */
    public void updateOnlyItem(ItemDto itemOnly) {
        this.brandName = itemOnly.getBrandName();
        this.itemName = itemOnly.getItemName();
        this.price = itemOnly.getPrice();
        this.stockQuantity = itemOnly.getStockQuantity();
    }
}
