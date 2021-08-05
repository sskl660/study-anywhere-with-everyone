package com.example.ssaziptest.domain.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadRequest {
    private String bucketName;
    private String downloadFileName;
    private String localFileLocation;
}
