package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.challenge.ChallengeCreateRequest;
import com.example.ssaziptest.domain.challenge.ChallengeDetailResponse;
import com.example.ssaziptest.domain.challenge.ChallengeEntity;
import com.example.ssaziptest.domain.challenge.ChallengeListResponse;
import com.example.ssaziptest.repository.ChallengeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Transactional
    public int createChallenge(ChallengeCreateRequest request) {
        return challengeRepository.save(request.toEntity()).getChallengeNo();
    }

    @Transactional
    public List<ChallengeListResponse> getChallengelist() {
        List<ChallengeEntity> challengeEntities = challengeRepository.findAll();
        List<ChallengeListResponse> challengeDtoList = new ArrayList<>();

        for (ChallengeEntity challengeEntity : challengeEntities) {
            // System.out.println(challengeEntity.getChallengeName());
            ChallengeListResponse challengeDto = ChallengeListResponse.builder()
                    .challengeNo(challengeEntity.getChallengeNo())
                    .challengeName(challengeEntity.getChallengeName())
                    .challengeCategory(challengeEntity.getChallengeCategory())
                    .challengeLevel(challengeEntity.getChallengeLevel())
                    .challengeCapacity(challengeEntity.getChallengeCapacity())
                    .challengeStartdate(challengeEntity.getChallengeStartdate())
                    .challengeEnddate(challengeEntity.getChallengeEnddate())
                    .challengeDesc(challengeEntity.getChallengeDesc())
                    .challengeTaskCnt(challengeEntity.getChallengeTaskCnt())
                    .build();
            challengeDtoList.add(challengeDto);
        }
        return challengeDtoList;
    }

    @Transactional
    public ChallengeDetailResponse getChallengeDetail(int challengeNo) {
        Optional<ChallengeEntity> challengeEntityTemp = challengeRepository.findById(challengeNo);
        ChallengeEntity challengeEntity = challengeEntityTemp.orElse(null);
        if(challengeEntity!=null){
            ChallengeDetailResponse challengeDetailResponse = ChallengeDetailResponse.builder()
                    .challengeNo(challengeEntity.getChallengeNo())
                    .challengeName(challengeEntity.getChallengeName())
                    .challengeCategory(challengeEntity.getChallengeCategory())
                    .challengeLevel(challengeEntity.getChallengeLevel())
                    .challengeCapacity(challengeEntity.getChallengeCapacity())
                    .challengeStartdate(challengeEntity.getChallengeStartdate())
                    .challengeEnddate(challengeEntity.getChallengeEnddate())
                    .challengeDesc(challengeEntity.getChallengeDesc())
                    .challengeTaskCnt(challengeEntity.getChallengeTaskCnt())
                    .build();
            return challengeDetailResponse;
        }
        else return null;
    }
}
