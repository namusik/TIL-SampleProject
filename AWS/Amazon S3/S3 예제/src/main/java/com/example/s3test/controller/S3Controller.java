package com.example.s3test.controller;

import com.example.s3test.dto.FileDto;
import com.example.s3test.model.FileEntity;
import com.example.s3test.service.FileService;
import com.example.s3test.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

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
    public String listPage(Model model) {
        List<FileEntity> fileList =fileService.getFiles();
        model.addAttribute("fileList", fileList);
        return "list";
    }

    @GetMapping("/api/folder")
    @ResponseBody
    public List<String> listFolder() {
        return s3Service.allFolders();
    }
}
