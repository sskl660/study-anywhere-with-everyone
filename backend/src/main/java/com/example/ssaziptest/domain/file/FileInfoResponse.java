package com.example.ssaziptest.domain.file;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileInfoResponse {
    private int fileNo;
    private String fileName;
    private String filePath;
    private String userEmail;
    private String fileInfo;
    private int fileType;

    @Builder
    public FileInfoResponse(int fileNo, String fileName, String filePath, String userEmail, String fileInfo, int fileType) {
        this.fileNo = fileNo;
        this.fileName = fileName;
        this.filePath = filePath;
        this.userEmail = userEmail;
        this.fileInfo = fileInfo;
        this.fileType = fileType;
    }
}
