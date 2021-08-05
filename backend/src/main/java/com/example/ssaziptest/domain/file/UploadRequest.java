package com.example.ssaziptest.domain.file;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadRequest {
    private String bucketName;
    private String uploadFileName;
    private String localFileLocation;
}
