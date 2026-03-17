package com.example.s3test.repository;

import com.example.s3test.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
