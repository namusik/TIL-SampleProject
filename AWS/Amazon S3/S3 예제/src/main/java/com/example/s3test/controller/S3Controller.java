package com.example.s3test.controller;

import com.example.s3test.dto.FileDto;
import com.example.s3test.service.FileService;
import com.example.s3test.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;
    private final FileService fileService;

    @GetMapping("/api/upload")
    public String goToUpload() {
        return "upload";
    }

    @PostMapping("/api/upload")
    public String uploadFile(FileDto fileDto) throws IOException {
        String url = s3Service.uploadFile(fileDto.getFile());

        fileDto.setUrl(url);
        fileService.save(fileDto);

        return "redirect:/api/list";
    }

    @GetMapping("/api/list")
    public String listPage() {

        return "list";
    }

}
