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

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemDto {

    private Long id;

    @NotNull(message = "브랜드명을 입력해 주세요.")
    @Length(min = 2, max = 20)
    private String brandName;

    @NotNull(message = "상품 이름을 입력해 주세요.")
    @Length(min = 2, max = 20)
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private int price;

    @NotNull
    @Max(9999)
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
