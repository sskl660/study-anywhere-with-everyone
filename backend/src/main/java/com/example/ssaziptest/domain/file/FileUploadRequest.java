package com.example.ssaziptest.domain.file;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@Data
@NoArgsConstructor
public class FileUploadRequest {

    private String fileName;
    private String fileOriginalname;
//    private String filePath;
    private String userEmail;
    private Blob fileData;
//    private String fileInfo;
//    private int fileType;

    @Builder
    public FileUploadRequest(String fileName, String fileOriginalname, String userEmail, Blob fileData) {
        this.fileName = fileName;
        this.fileOriginalname = fileOriginalname;
        this.userEmail = userEmail;
        this.fileData = fileData;
    }
}
