package com.nklcb.kream.service;

import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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


    /**
     * 등록한 상품 전체 리스트
     */
    public Page<ItemQueryDto> findAllList(Pageable pageable) {
        return itemRepository.findAllList(pageable);
    }


    /**
     * 상품 수정 시  @PathVariable id  조회
     */
    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
    }

    /**
     * 상품 삭제(id 검색)
     */
    @Transactional
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }


    /**
     * id로 ItemQueryDto로 변환해서 조회
     */
    public ItemQueryDto findByIdDto(Long id) {
        return itemRepository.findByIdDto(id);
    }


    /**
     * Item + UploadFile 수정시 사용
     */
    @Transactional
    public void updateItem(ItemQueryDto item) throws Exception {
        Item findItem = itemRepository.findById(item.getId()).orElseThrow(Exception::new);
        findItem.updateItem(item);
    }

    /**
     * uploadFile 변경x  Only Item Entity 수정시 사용
     */
    @Transactional
    public void updateOnlyItem(ItemQueryDto itemOnly) throws Exception {
        Item item = findById(itemOnly.getId()).orElseThrow(Exception::new);
        item.updateOnlyItem(itemOnly);
    }
}
