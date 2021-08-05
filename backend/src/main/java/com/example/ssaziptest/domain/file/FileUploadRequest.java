package com.example.ssaziptest.domain.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileUploadRequest {

    private String fileName;
    private String fileOriginalname;
    private String filePath;
    private String userEmail;
//    private String fileInfo;
//    private int fileType;
}
