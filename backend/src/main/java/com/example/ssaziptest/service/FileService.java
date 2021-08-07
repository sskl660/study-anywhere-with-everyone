//package com.example.ssaziptest.service;
//
//import com.example.ssaziptest.domain.file.FileEntity;
//import com.example.ssaziptest.domain.file.FileInfoResponse;
//import com.example.ssaziptest.domain.file.FileUploadRequest;
//import com.example.ssaziptest.repository.FileRepository;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//@NoArgsConstructor
//@AllArgsConstructor
//public class FileService {
//    @Autowired
//    private FileRepository fileRepository;
//
//    @Transactional
//    public int fileUpload(FileUploadRequest request){
//
//        FileEntity fileEntity = FileEntity.builder()
//                .fileUseremail(request.getUserEmail())
//                .fileData(request.getFileData())
//                .build();
//        FileEntity fileEntity1 = fileRepository.save(fileEntity);
//        return fileEntity1.getFileNo();
//    }
//
//    @Transactional
//    public FileInfoResponse fileDownload(int fileNo){
//        FileEntity file = fileRepository.findById(fileNo).get();
//        FileInfoResponse fileInfoResponse = FileInfoResponse.builder()
//                .fileNo(fileNo)
//                .userEmail(file.getFileUseremail())
//                .build();
//        return fileInfoResponse;
//    }
//
//
//}
