package com.nklcb.kream.controller.admin;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    /**
     * 등록한 상품 전체 리스트
     */
    @GetMapping("/productManagement")
    public String adminProductManagement(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {

        log.info("in AdminController");

        Page<ItemDto> findList = itemService.findAllList(pageable);

        int startPage = Math.max(1, findList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(findList.getTotalPages(), findList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("items", findList);

        log.info("findList = {}", findList);

        return "item/admin-itemList";
    }

    @GetMapping("/uploadItem")
    public String uploadPage(@ModelAttribute(name = "item") ItemDto itemDto) {
        log.info("GET uploadPage");
        return "item/uploadItem";
    }


    @PostMapping("/uploadItem")
    public String itemSave(@Valid ItemDto itemDto, BindingResult bindingResult){
        log.info("POST uploadPage");
        if (bindingResult.hasErrors()){
            log.info("itemDto = {}",itemDto);
            return "/item/uploadItem";
        }
            Item newItem = Item.addItem(itemDto.getBrandName(), itemDto.getItemName(), itemDto.getPrice(), itemDto.getStockQuantity(), LocalDateTime.now());
            log.info("newItem = {}", newItem);
            itemService.save(newItem);

            log.info("item save success");
            return "redirect:/";
    }
}
