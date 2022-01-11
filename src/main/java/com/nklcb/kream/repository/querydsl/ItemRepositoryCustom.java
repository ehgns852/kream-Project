package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    Page<ItemDto> findAllList(Pageable pageable);
}
