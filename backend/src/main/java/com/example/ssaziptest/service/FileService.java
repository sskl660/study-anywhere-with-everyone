package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.file.FileEntity;
import com.example.ssaziptest.domain.file.FileInfoResponse;
import com.example.ssaziptest.domain.file.FileUploadRequest;
import com.example.ssaziptest.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.transaction.Transactional;
import java.io.File;

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

    @Transactional
<<<<<<< HEAD
    public FileInfoResponse getFile(int fileNo){
=======
    public FileInfoResponse fileDownload(int fileNo){
>>>>>>> deccdb86eacf0c86c982fdf0273a3553e5dcda9d
        FileEntity file = fileRepository.findById(fileNo).get();
        FileInfoResponse fileInfoResponse = FileInfoResponse.builder()
                .fileNo(fileNo)
                .fileName(file.getFileName())
                .filePath(file.getFilePath())
                .userEmail(file.getFileUseremail())
                .build();
        return fileInfoResponse;
    }


}
