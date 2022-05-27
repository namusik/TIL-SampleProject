package com.example.s3test.service;

import com.example.s3test.dto.FileDto;
import com.example.s3test.model.FileEntity;
import com.example.s3test.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;


    public void save(FileDto fileDto) {
        FileEntity fileEntity = new FileEntity(fileDto.getTitle(), fileDto.getUrl());
        fileRepository.save(fileEntity);
    }
}
