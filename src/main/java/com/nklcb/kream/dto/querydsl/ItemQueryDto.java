package com.nklcb.kream.dto.querydsl;

import com.nklcb.kream.entity.item.UploadFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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

    private String uploadFileName;

    private String storeFileName;

    private String filePath;

    private List<MultipartFile> files;


    @QueryProjection
    public ItemQueryDto(Long id,String brandName, String itemName, int price, int stockQuantity, LocalDateTime createDate, String uploadFileName, String storeFileName, String filePath) {
        this.id = id;
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.createDate = createDate;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.filePath = filePath;
    }

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