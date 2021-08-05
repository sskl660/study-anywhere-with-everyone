package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.file.DownloadRequest;
import com.example.ssaziptest.domain.file.UploadRequest;
import com.example.ssaziptest.service.GCSService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.example.ssaziptest.service"})
@Api(tags = {"9.Google Cloud Service"})
public class GCSController {
    private final GCSService gcsService;


    @ApiOperation(value = "gcs to local 다운로드")
    @PostMapping("gcs/download")
    public ResponseEntity localDownloadFromStorage(@RequestBody DownloadRequest downloadReqDto){

        Blob fileFromGCS = gcsService.downloadFileFromGCS(downloadReqDto.getBucketName(), downloadReqDto.getDownloadFileName(), downloadReqDto.getLocalFileLocation());
        return ResponseEntity.ok(fileFromGCS.toString());
    }
    @ApiOperation(value = "local to gcs 업로드")
    @PostMapping("gcs/upload")
    public ResponseEntity localUploadToStorage(@RequestBody UploadRequest uploadReqDto) throws Exception {
        BlobInfo fileFromGCS = gcsService.uploadFileToGCS(uploadReqDto);
        return ResponseEntity.ok(fileFromGCS.toString());
    }

}
