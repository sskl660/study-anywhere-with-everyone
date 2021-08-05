package com.example.ssaziptest.domain.file;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "file")
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileNo;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path", length = 500)
    private String filePath;

    @Column(name = "file_useremail")
    private String fileUseremail;

    @Builder
    public FileEntity(int fileNo, String fileName, String filePath, String fileUseremail) {
        this.fileNo = fileNo;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileUseremail = fileUseremail;
    }
}
