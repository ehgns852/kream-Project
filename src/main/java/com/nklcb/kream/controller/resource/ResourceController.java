package com.nklcb.kream.controller.resource;

import com.nklcb.kream.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ResourceController {

    private final FileStore fileStore;

    /**
     * 상품 렌더링 경로
     */
    @ResponseBody
    @GetMapping("/image/{filename}")
    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
        log.info("in Resource image");
        if (!filename.equals("null")) {
            return new UrlResource("file:" + fileStore.getFullPath(filename));
        } else {
            return new UrlResource("https://dummyimage.com/450x300/dee2e6/6c757d.jpg");
        }
    }
}
