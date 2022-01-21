package com.nklcb.kream.service;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.entity.item.UploadFile;
import com.nklcb.kream.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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
    public Item findById(Long id) throws Exception {
        return itemRepository.findById(id).orElseThrow(Exception::new);
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
     * id의 null 검증 후 model에 각각 반환
     */
    public void getItem(Long id, Model model) {
        if (id == null) {
            ItemDto item = new ItemDto();
            log.info("new ItemDto = {}", item);
            model.addAttribute("item", item);
        } else {
            ItemQueryDto findItem = findByIdDto(id);

            log.info("findItem = {}", findItem);
            model.addAttribute("item", findItem);
        }
    }

    /**
     * uploadFile이 null이 아니라면 updateQuery, 있다면 uploadFile을 제외한 entity update
     * Item + UploadFile 수정시 사용
     */
    @Transactional
    public void updateItem(ItemDto itemDto, UploadFile uploadFile) throws Exception {
        Item findItem = findById(itemDto.getId());
        if (uploadFile != null) {
            findItem.updateItem(itemDto,uploadFile);
        } else {
            findItem.updateOnlyItem(itemDto);
        }
    }

        /**
         * id가 null 이라면 새로운 item 생성
         */
    @Transactional
    public Item buildItem(ItemDto itemDto, UploadFile uploadFile) {
            return Item.builder()
                    .brandName(itemDto.getBrandName())
                    .itemName(itemDto.getItemName()).price(itemDto.getPrice())
                    .stockQuantity(itemDto.getStockQuantity())
                    .createDate(LocalDateTime.now())
                    .attachFile(uploadFile)
                    .build();
        }


    public Page<ItemQueryDto> findBestItem(Pageable pageable) {
        return itemRepository.findBestItem(pageable);
    }

}
