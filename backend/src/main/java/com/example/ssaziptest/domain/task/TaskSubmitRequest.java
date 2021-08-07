package com.example.ssaziptest.domain.task;

import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.repository.ChallengeRepository;
import com.example.ssaziptest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@Data
@NoArgsConstructor
public class TaskSubmitRequest {

    private int taskIndex;
    private String taskContent;
    private String taskDesc;
//    private MultipartFile taskImage;
//    private MultipartFile taskFile;
    private String userEmail;
    private int challengeNo;
}
