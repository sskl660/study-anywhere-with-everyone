package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.file.FileEntity;
import com.example.ssaziptest.domain.file.FileUploadRequest;
import com.example.ssaziptest.domain.user.UserEntity;
<<<<<<< HEAD
=======
import com.example.ssaziptest.repository.FileRepository;
>>>>>>> deccdb86eacf0c86c982fdf0273a3553e5dcda9d
import com.example.ssaziptest.repository.UserRepository;
import com.example.ssaziptest.service.FileService;
import com.example.ssaziptest.util.MD5Generator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Api(tags = {"8.File"})
@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private UserRepository userRepository;
<<<<<<< HEAD
=======
    @Autowired
    private FileRepository fileRepository;
>>>>>>> deccdb86eacf0c86c982fdf0273a3553e5dcda9d

    /*프로필 이미지 업로드*/
    @PostMapping(value = "/upload/profile/{useremail}")
    public int uploadProfile(@RequestParam("file") MultipartFile files, @PathVariable(name = "useremail")String useremail) {
<<<<<<< HEAD
=======
        int fileId = 0;
>>>>>>> deccdb86eacf0c86c982fdf0273a3553e5dcda9d
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

<<<<<<< HEAD
            int fileId = fileService.fileUpload(fileDto);
=======
            fileId = fileService.fileUpload(fileDto);
<<<<<<< HEAD
>>>>>>> deccdb86eacf0c86c982fdf0273a3553e5dcda9d
            //user나 task쪽 service로도 저장
=======
>>>>>>> ed4d190e6639a9cd883f0ec6781d5035c9a49a6a
            UserEntity userEntity = userRepository.getById(useremail);
            userEntity.setUserImage(Integer.toString(fileId));
            userRepository.save(userEntity);
        } catch(Exception e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        return 0;
    }
=======
        return fileId;
    }

<<<<<<< HEAD
>>>>>>> deccdb86eacf0c86c982fdf0273a3553e5dcda9d
=======
    @ApiOperation(value = "image 조회")
    @GetMapping(value = "image/{fileno}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable("fileno") int fileno) throws Exception {
        FileEntity fileEntity = fileRepository.getById(fileno);
        InputStream imageStream = new FileInputStream(fileEntity.getFilePath());
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }

>>>>>>> ed4d190e6639a9cd883f0ec6781d5035c9a49a6a
}
