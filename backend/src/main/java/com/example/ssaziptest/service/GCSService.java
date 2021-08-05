package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.file.UploadRequest;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GCSService {

    String path1 = "/Users/heewonyouh/Desktop/ssazip-6140e2fdcd5c.json";
    String path = ClassLoader.getSystemResource("ssazip-6140e2fdcd5c.json").getPath();
    Storage storage;

    {

        try {
            storage = StorageOptions.newBuilder().setProjectId("ssazip")
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(path)))
                    .build().getService();
        } catch (Exception e) {
            System.out.println(ClassLoader.getSystemResource("ssazip-6140e2fdcd5c.json").getPath());
            e.printStackTrace();

        }

    }

    public Blob downloadFileFromGCS(String bucketName, String downloadFileName, String localFileLocation) {

        Blob blob = storage.get(bucketName, downloadFileName);
        blob.downloadTo(Paths.get(localFileLocation));
        return blob;
    }
    @SuppressWarnings("deprecation")
    public BlobInfo uploadFileToGCS(UploadRequest uploadReqDto) throws Exception {


        Storage storage = StorageOptions.newBuilder().setProjectId("ssazip")
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(path)))
                .build().getService();
        BlobId blobId = BlobId.of(uploadReqDto.getBucketName(), uploadReqDto.getUploadFileName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.READER))))
                .build();
        storage.create(blobInfo, Files.readAllBytes(Paths.get(uploadReqDto.getLocalFileLocation())));

        return blobInfo;
    }
}
