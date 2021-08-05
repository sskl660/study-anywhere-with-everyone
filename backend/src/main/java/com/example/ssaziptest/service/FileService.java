package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.file.FileEntity;
import com.example.ssaziptest.domain.file.FileUploadRequest;
import com.example.ssaziptest.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    @Transactional
    public int fileUpload(FileUploadRequest request){

        FileEntity fileEntity = FileEntity.builder()
                .fileName(request.getFileName())
                .filePath(request.getFilePath())
                .fileUseremail(request.getUserEmail())
                .build();
        FileEntity fileEntity1 = fileRepository.save(fileEntity);
        return fileEntity1.getFileNo();
    }

}
