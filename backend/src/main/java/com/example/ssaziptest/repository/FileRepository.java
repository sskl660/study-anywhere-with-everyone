package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.file.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {
    FileEntity findByFileUseremailAndFileInfo(String userEmail, String fileInfo);
    List<FileEntity> findByFileUseremailAndAndFileInfo(String userEmail, String fileInfo);
}
