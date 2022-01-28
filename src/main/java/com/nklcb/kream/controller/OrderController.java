package com.nklcb.kream.controller;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.dto.OrderDto;
import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/item")
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;


    /**
     * 사용자 - 상품 상세 화면
     */
    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable(name = "itemId") Long id, Model model) throws Exception {
        ItemQueryDto findItem = itemService.findByIdDto(id);
        OrderDto orderDto = new OrderDto();
        model.addAttribute("item", findItem);
        model.addAttribute("order", orderDto);
        log.info("UserITemController In");
        return "item/user-itemDetail";
    }

    @PostMapping("/{itemId}")
    public String order(@ModelAttribute(name = "order")OrderDto orderDto,
                        @ModelAttribute(name = "item")ItemDto itemDto){

//        itemService.save();
        return null;
    }



}
