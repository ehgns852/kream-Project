package com.nklcb.kream.dto;

import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.entity.item.UploadFile;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemDto {

    private Long id;

    @NotNull
    @Length(min = 2, max = 20, message = "길이가 2자에서 20자 사이여야 합니다.")
    private String brandName;

    @NotNull
    @Length(min = 2, max = 20, message = "길이가 2자에서 20자 사이여야 합니다.")
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000, message = "최소 금액이 1000원에서 최대 금액 1000000원 사이여아 합니다.")
    private int price;

    @NotNull(message = "재고수량을 입력해 주세요.")
    @Range(min = 1, max = 9999,message = "최소 수량 1개에서 최대 수량 9999개 까지입니다.")
    private int stockQuantity;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Nullable
    private MultipartFile file;

    private String uploadFileName;

    private String storeFileName;

    private String filePath;


    /**
     * 수정 메서드
     */
    public ItemDto updateItem(UploadFile uploadFile) {
        this.uploadFileName = uploadFile.getUploadFileName();
        this.storeFileName = uploadFile.getStoreFileName();
        this.filePath = uploadFile.getFilePath();

        return this;
    }
}
