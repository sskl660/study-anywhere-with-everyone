package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.file.FileEntity;
import com.example.ssaziptest.domain.file.FileUploadRequest;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.UserRepository;
import com.example.ssaziptest.service.FileService;
import com.example.ssaziptest.util.MD5Generator;
import io.swagger.annotations.Api;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

@Api(tags = {"8.File"})
@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserRepository userRepository;

    /*프로필 이미지 업로드*/
    @PostMapping(value = "/upload/{useremail}")
    public int uploadProfile(@RequestParam("file") MultipartFile files, @PathVariable(name = "useremail")String useremail) {
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            FileUploadRequest fileDto = new FileUploadRequest();
            fileDto.setFileOriginalname(origFilename);
            fileDto.setFileName(filename);
            fileDto.setFilePath(filePath);

            fileDto.setUserEmail(useremail);

            int fileId = fileService.fileUpload(fileDto);
            //user나 task쪽 service로도 저장
            UserEntity userEntity = userRepository.getById(useremail);
            userEntity.setUserImage(Integer.toString(fileId));
            userRepository.save(userEntity);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
