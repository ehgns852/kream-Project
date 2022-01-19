package com.nklcb.kream.controller;


import com.nklcb.kream.dto.querydsl.ItemQueryDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;


@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;
    private final FileStore fileStore;

    @GetMapping("/")
    public String index(@PageableDefault(page = 0, size = 4) Pageable pageable, Model model) {

        Page<ItemQueryDto> findItems = itemService.findBestItem(pageable);

        log.info("itemList = {}", findItems);

        model.addAttribute("items", findItems);

        return "index";
    }

    /**
     * 상품 업로드시 렌더링 경로 controller
     */
    @ResponseBody
    @GetMapping("/home/image/{filename}")
    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
        log.info("in Resource image");
        if (!filename.equals("null")) {
            return new UrlResource("file:" + fileStore.getFullPath(filename));
        } else {
            return new UrlResource("https://dummyimage.com/450x300/dee2e6/6c757d.jpg");
        }
    }

}
