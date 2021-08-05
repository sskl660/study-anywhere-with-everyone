package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.file.FileUploadRequest;
import com.example.ssaziptest.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Api(tags = {"8.File"})
@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public int fileUpload(FileUploadRequest request) throws Exception{
        return fileService.fileUpload(request);
    }
}
