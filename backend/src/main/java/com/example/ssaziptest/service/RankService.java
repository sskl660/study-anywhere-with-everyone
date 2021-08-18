package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.rank.ChallengeTopEntity;
import com.example.ssaziptest.domain.rank.GalaxyTopEntity;
import com.example.ssaziptest.domain.rank.GradsResponse;
import com.example.ssaziptest.domain.rank.RankResponse;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.ChallengeTopRepository;
import com.example.ssaziptest.repository.GalaxyTopRepository;
import com.example.ssaziptest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RankService {

    @Autowired
    private ChallengeTopRepository challengeTopRepository;
    @Autowired
    private GalaxyTopRepository galaxyTopRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public List<RankResponse> galaxytop5(){
        List<GalaxyTopEntity> galaxyTopEntities = galaxyTopRepository.findAll(Sort.by(Sort.Direction.DESC,"galaxytopWeektime"));
        List<RankResponse> responses = new ArrayList<>();
        for(GalaxyTopEntity galaxyTopEntity: galaxyTopEntities){
            RankResponse response = RankResponse.builder()
                    .userEmail(galaxyTopEntity.getGalaxytopEmail())
                    .userName(galaxyTopEntity.getGalaxytopName())
                    .week(galaxyTopEntity.getGalaxytopWeektime())
                    .build();
            responses.add(response);
        }
        return responses;
    }
    @Transactional
    public List<RankResponse> challengetop5(){
        List<ChallengeTopEntity> challengeTopEntities = challengeTopRepository.findAll(Sort.by(Sort.Direction.DESC,"challengetopWeekcnt"));
        List<RankResponse> responses = new ArrayList<>();
        for(ChallengeTopEntity challengeTopEntity: challengeTopEntities){
            RankResponse response = RankResponse.builder()
                    .userEmail(challengeTopEntity.getChallengetopEmail())
                    .userName(challengeTopEntity.getChallengetopName())
                    .week(challengeTopEntity.getChallengetopWeekcnt())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Transactional
    public List<GradsResponse> getGraduates(){
        List<UserEntity> userEntities = userRepository.findRandomGraduates();
        List<GradsResponse> responses = new ArrayList<>();
        for(UserEntity userEntity:userEntities) {
            GradsResponse response = new GradsResponse();
            response.setUserEmail(userEntity.getUserEmail());
            response.setUserName(userEntity.getUserName());
            responses.add(response);
        }
        return responses;
    }

//    @Scheduled(cron = "0 13 0 * * *")
    @Scheduled(cron = "0 0 0 ? * 2")
    @Transactional
    public void runEveryMonday(){
        galaxyTopRepository.deleteAll();
        List<UserEntity> galaxyTop5 = userRepository.findTop5ByOrderByUserWeektimeDesc();
        List<UserEntity> challengeTop5 = userRepository.findTop5ByOrderByUserWeekcompleteDesc();
        for(UserEntity userEntity:galaxyTop5){
            GalaxyTopEntity galaxyTopEntity = GalaxyTopEntity.builder()
                    .galaxytopEmail(userEntity.getUserEmail())
                    .galaxytopName(userEntity.getUserName())
                    .galaxyWeektime(userEntity.getUserWeektime())
                    .build();
            galaxyTopRepository.save(galaxyTopEntity);
        }
        challengeTopRepository.deleteAll();
        for(UserEntity userEntity:challengeTop5){
            System.out.println(userEntity.getUserWeekcomplete());
            ChallengeTopEntity challengeTopEntity = ChallengeTopEntity.builder()
                    .challengetopEmail(userEntity.getUserEmail())
                    .challengetopName(userEntity.getUserName())
                    .challengetopWeekcnt(userEntity.getUserWeekcomplete())
                    .build();
            challengeTopRepository.save(challengeTopEntity);
        }
        List<UserEntity> userEntities = userRepository.findAll();
        for(UserEntity userEntity: userEntities){
            userEntity.setUserWeekcomplete(0);
            userEntity.setUserWeektime(0);
            userRepository.save(userEntity);
        }
    }

}
