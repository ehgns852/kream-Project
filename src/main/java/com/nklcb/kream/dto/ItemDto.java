package com.nklcb.kream.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemDto {

    private Long id;

    @NotNull
    @Length(min = 3, max = 20)
    private String brandName;

    @NotNull
    private String itemName;

    @NotNull
    private int price;

    @NotNull
    private int stockQuantity;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private MultipartFile file;

    private List<MultipartFile> imageFiles;


}
