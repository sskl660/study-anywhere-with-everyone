package com.example.ssaziptest.service;

import com.example.ssaziptest.domain.user.LoginRequest;
import com.example.ssaziptest.domain.user.UserCreateRequest;
import com.example.ssaziptest.domain.user.UserDetailResponse;
import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUser(UserCreateRequest request){
        userRepository.save(request.toEntity());
    }

    @Transactional
    public boolean checkEmail(String user_email){
       Optional<UserEntity> userEntity = userRepository.findById(user_email);
       //중복일때 false, 사용가능할때 true
        System.out.println(userEntity);
       return userEntity.isEmpty()?true:false;
    }

    @Transactional
    public UserEntity login(String userEmail, String userPassword){
        Optional<UserEntity> userEntity = userRepository.findUserEntityByUserEmailAndUserPassword(userEmail, userPassword);
        return userEntity.orElse(null);
    }
    @Transactional
    public UserDetailResponse findUserById(String userEmail){
        Optional<UserEntity> userEntityTemp = userRepository.findById(userEmail);
        UserEntity userEntity = userEntityTemp.orElse(null);
        if(userEntity!=null){
            UserDetailResponse userDetailResponse = UserDetailResponse.builder()
                    .userEmail(userEntity.getUserEmail())
                    .userName(userEntity.getUserName())
                    .userNumber(userEntity.getUserNumber())
                    .userTerm(userEntity.getUserTerm())
                    .userGraduated(userEntity.getUserGraduated())
                    .userTotalcomplete(userEntity.getUserTotalcomplete())
                    .userImage(userEntity.getUserImage())
                    .userGit(userEntity.getUserGit())
                    .userBlog(userEntity.getUserBlog())
                    .userDevstyle(userEntity.getUserDevstyle())
                    .userMbti(userEntity.getUserMbti())
                    .userWishfield(userEntity.getUserWishfield())
                    .userIntroduce(userEntity.getUserIntroduce())
                    .userTechstack(userEntity.getUserTechstack())
                    .userTotaltime(userEntity.getUserTotaltime())
                    .userWeektime(userEntity.getUserWeektime())
                    .userWeekcomplete(userEntity.getUserWeekcomplete())
                    .userFollower(userEntity.getUserFollower())
                    .userFollowing(userEntity.getUserFollowing())
                    .build();
            return userDetailResponse;
        }
        return null;
    }
}
