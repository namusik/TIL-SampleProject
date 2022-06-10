package com.example.localstack.service;

import com.example.localstack.dto.FileDto;
import com.example.localstack.model.FileEntity;
import com.example.localstack.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void save(FileDto fileDto) {
        FileEntity fileEntity = new FileEntity(fileDto.getTitle(), fileDto.getUrl());
        fileRepository.save(fileEntity);
    }

    public List<FileEntity> getFiles() {
        List<FileEntity> all = fileRepository.findAll();
        return all;
    }
}
