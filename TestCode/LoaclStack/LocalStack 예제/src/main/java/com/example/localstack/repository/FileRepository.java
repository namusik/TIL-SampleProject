package com.example.localstack.repository;

import com.example.localstack.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
