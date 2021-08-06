package com.example.ssaziptest.domain.file;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@NoArgsConstructor
public class ProfileUploadRequest {

    private String fileName;
    private String fileOriginalname;
//    private String filePath;
    private String userEmail;
    private Blob fileData;
//    private String fileInfo;
//    private int fileType;
}
