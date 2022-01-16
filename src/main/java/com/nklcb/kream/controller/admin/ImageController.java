package com.nklcb.kream.controller.admin;

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
public class ImageController {

    private final FileStore fileStore;


    @GetMapping("/image/{filename}")
    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
        log.info("in Resource image");
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }
}
