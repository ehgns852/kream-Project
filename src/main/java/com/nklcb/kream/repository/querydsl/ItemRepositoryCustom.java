package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    /**
     * 등록한 상품 전체 리스트
     */
    Page<ItemQueryDto> findAllList(Pageable pageable);

    /**
     * id로 ItemQueryDto 변환해서 조회
     */
    ItemQueryDto findByIdDto(Long id);

}
