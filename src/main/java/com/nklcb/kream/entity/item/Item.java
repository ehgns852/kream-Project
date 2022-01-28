package com.nklcb.kream.entity.item;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.dto.OrderDto;
import com.nklcb.kream.entity.ItemCart;
import com.nklcb.kream.entity.OrderItem;
import com.nklcb.kream.entity.audit.AuditListener;
import com.nklcb.kream.entity.audit.Auditable;
import com.nklcb.kream.entity.embedded.TimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditListener.class)
@ToString(of = {"id","brandName","itemName","price","stockQuantity","createDate"})
public class Item implements Auditable{

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

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<ItemCart> itemCarts = new ArrayList<>();


    protected Item(String brandName, String itemName, int price, int stockQuantity) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @Builder
    public Item(String brandName, String itemName, int price, int stockQuantity, TimeEntity timeEntity, UploadFile attachFile, List<OrderItem> orderItems, List<ItemCart> itemCarts) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.timeEntity = timeEntity;
        this.attachFile = attachFile;
        this.orderItems = orderItems;
        this.itemCarts = itemCarts;
    }

    @Builder


    /**
     * item 생성 팩토리 메서드
     */
    public static Item addItem(String brandName, String itemName, int price, int stockQuantity) {
        return new Item(brandName, itemName, price, stockQuantity);
    }


    /**
     *
     * @param itemDto
     * @param orderDto
     * @return
     */
    public static Item addItemOrder(ItemDto itemDto,OrderDto orderDto){
        return Item.builder()
                .brandName(itemDto.getBrandName())
                .itemName(itemDto.getItemName())
                .price(itemDto.getPrice())
                .stockQuantity(itemDto.getStockQuantity())
                .attachFile((UploadFile) itemDto.getFile())
                .build();
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


    /**
     * AuditListener 생성 시간 자동 생성 및 변경
     */
    public void setTimeEntity(TimeEntity timeEntity) {
        this.timeEntity = timeEntity;
    }
}
