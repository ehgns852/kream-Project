package com.nklcb.kream.controller.api;

import com.nklcb.kream.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemApiController {

    private final ItemService itemService;

    @DeleteMapping("deleteItem/{itemId}")
    public void deleteItem(@PathVariable Long itemId){
        itemService.deleteById(itemId);
    }


}
