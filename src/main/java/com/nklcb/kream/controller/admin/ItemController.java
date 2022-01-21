package com.nklcb.kream.controller.admin;

import com.nklcb.kream.dto.ItemDto;
import com.nklcb.kream.dto.querydsl.ItemQueryDto;
import com.nklcb.kream.entity.item.Item;
import com.nklcb.kream.entity.item.UploadFile;
import com.nklcb.kream.file.FileStore;
import com.nklcb.kream.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
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
        itemService.getItem(id, model);
        log.info("GET uploadPage");
        return "item/uploadItem";
    }





    /**
     * 상품 등록
     */
    @PostMapping("/uploadItem")
    public String addItem(@ModelAttribute(name = "item") @Valid ItemDto itemDto, BindingResult bindingResult,Model model) throws Exception {

        log.info("POST uploadPage");
        log.info("itemDto.getFile = {}", itemDto.getFile());

        if (bindingResult.hasErrors()) {
            log.info("itemDto = {}", itemDto);
            log.info("hasError = {}", bindingResult.hasErrors());
            return "/item/uploadItem";
        }
        log.info("itemDto.id = {}", itemDto.getId());

        UploadFile uploadFile = fileStore.storeFile(itemDto.getFile());

        if (itemDto.getId() == null) {
            Item newItem = itemService.buildItem(itemDto, uploadFile);
            log.info("newItem = {}", newItem);
            itemService.save(newItem);
            log.info("item save success");
        } else{
            itemService.updateItem(itemDto, uploadFile);
            log.info("updateItem");
        }


        return "redirect:/admin/productManagement";
    }


}
