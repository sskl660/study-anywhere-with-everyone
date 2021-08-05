package com.example.ssaziptest.domain.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileUploadRequest {

    private String fileName;
    private String filePath;
    private String userEmail;
}
