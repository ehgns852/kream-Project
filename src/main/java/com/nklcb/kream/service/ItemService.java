package com.nklcb.kream.service;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;


    /**
     * 저장
     */
    @Transactional
    public Item save(Item item){
        return itemRepository.save(item);
    }

    public Page<ItemDto> findAllList(Pageable pageable) {
        return itemRepository.findAllList(pageable);
    }


}
