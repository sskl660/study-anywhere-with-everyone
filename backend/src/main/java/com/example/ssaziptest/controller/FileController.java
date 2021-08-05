package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.file.FileEntity;
import com.example.ssaziptest.domain.file.FileUploadRequest;
import com.example.ssaziptest.service.FileService;
import io.swagger.annotations.Api;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

@Api(tags = {"8.File"})
@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public int fileUpload(@RequestPart MultipartFile file) {
//        FileEntity fileEntity = new FileEntity();
//        String sourceFileName = file.getOriginalFilename();
//        String sourceFileNameExtension =  FilenameUtils.getExtension(sourceFileName).toLowerCase();
//        File destinationFile;
//        String destinationFileName;
//        String fileUrl
        return  0;
    }
}
