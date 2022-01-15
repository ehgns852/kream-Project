package com.nklcb.kream.controller.admin;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.entity.item.UploadFile;
import com.nklcb.kream.file.FileStore;
import com.nklcb.kream.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ItemController {

    private final FileStore fileStore;

    private final ItemService itemService;


    /**
     * 등록한 상품 전체 리스트
     */
    @GetMapping("/productManagement")
    public String adminProductManagement(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {

        log.info("in AdminController");

        Page<ItemQueryDto> findList = itemService.findAllList(pageable);

        int startPage = Math.max(1, findList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(findList.getTotalPages(), findList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("items", findList);

        log.info("findList = {}", findList);

        return "item/admin-itemList";
    }


    /**
     * 상품 등록, 수정 페이지
     */
    @GetMapping("/uploadItem")
    public String uploadPage(@RequestParam(required = false) Long id, Model model) throws Exception {
        if (id == null) {
            ItemQueryDto item = new ItemQueryDto();
            model.addAttribute("item", item);
        } else {
            ItemQueryDto findItem = itemService.findByIdDto(id);

            log.info("findItem = {}", findItem);
            model.addAttribute("item", findItem);
        }
        log.info("GET uploadPage");
        return "item/uploadItem";
    }


    /**
     * 상품 등록
     */
    @PostMapping("/uploadItem")
    public String addItem(@ModelAttribute(name = "item") @Valid ItemDto itemDto, BindingResult bindingResult,
                          ItemQueryDto itemQueryDto) throws Exception {
        log.info("POST uploadPage");

        if (bindingResult.hasErrors()) {
            log.info("itemDto = {}", itemDto);
            return "/item/uploadItem";
        }

        log.info("itemDto.id = {}", itemDto.getId());


        if (itemDto.getId() == null) {
            UploadFile uploadFile = fileStore.storeFile(itemDto.getFile());
            Item newItem = Item.builder()
                    .brandName(itemDto.getBrandName())
                    .itemName(itemDto.getItemName()).price(itemDto.getPrice())
                    .stockQuantity(itemDto.getStockQuantity())
                    .createDate(LocalDateTime.now())
                    .attachFile(uploadFile)
                    .build();

            log.info("newItem = {}", newItem);
            itemService.save(newItem);

            log.info("item save success");

        } else{
            UploadFile uploadFile = fileStore.storeFile(itemQueryDto.getFile());
            if (uploadFile != null) {
                log.info("uploadFile = {}",uploadFile);
                ItemQueryDto updateItem = itemQueryDto.updateItem(uploadFile);
                log.info("updateItem = {}", updateItem);
                itemService.updateItem(updateItem);
            } else {
                itemService.updateOnlyItem(itemQueryDto);
            }
        }


        return "redirect:/admin/productManagement";
    }
}
