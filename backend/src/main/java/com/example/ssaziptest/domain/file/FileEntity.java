//package com.example.ssaziptest.domain.file;
//
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.persistence.*;
//import java.sql.Blob;
//
//@Data
//@NoArgsConstructor
//@Entity(name = "file")
//@Table(name = "file")
//public class FileEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int fileNo;
//
////    @Column(name = "file_name")
////    private String fileName;
//
////    @Column(name = "file_size")
////    private int fileSize;
////
////    @Column(name = "file_originalname")
////    private String fileOriginalname;
//
////    @Column(name = "file_path", length = 500)
////    private String filePath;
//
//    @Column(name = "file_useremail")
//    private String fileUseremail;
//
////    //프로필인지 taskNo인지
////    @Column(name = "file_info")
////    private String fileInfo;
//
////    //1: 이미지 2: file
////    @Column(name = "file_type")
////    private int fileType;
//
//    @Lob
//    @Column(name = "file_data")
//    private Blob fileData;
//
//    @Builder
//    public FileEntity(int fileNo, String fileUseremail, Blob fileData) {
//        this.fileNo = fileNo;
////        this.fileName = fileName;
////        this.fileSize = fileSize;
////        this.fileOriginalname = fileOriginalname;
//        this.fileUseremail = fileUseremail;
////        this.fileInfo = fileInfo;
////        this.fileType = fileType;
//        this.fileData = fileData;
//    }
//}
