package com.nklcb.kream.controller;

import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.file.FileStore;
import com.nklcb.kream.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;

@Controller
@Slf4j
@RequestMapping("/item")
@RequiredArgsConstructor
public class UserItemController {

    private final ItemService itemService;


    /**
     * 사용자 - 상품 상세 화면
     */
    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable(name = "itemId") Long id, Model model) throws Exception{
        ItemQueryDto findItem = itemService.findByIdDto(id);
        model.addAttribute("item", findItem);
        log.info("UserITemController In");
        return "item/user-itemDetail";
    }

}
