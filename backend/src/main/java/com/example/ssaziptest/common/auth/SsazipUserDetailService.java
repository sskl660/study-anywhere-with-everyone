package com.example.ssaziptest.common.auth;


import com.example.ssaziptest.domain.user.UserEntity;
import com.example.ssaziptest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */
@Component
public class SsazipUserDetailService implements UserDetailsService{
	@Autowired
	UserService userService;
	
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		UserEntity user = userService.getUserByUserId(userEmail);
    		if(user != null) {
				SsazipUserDetails userDetails = new SsazipUserDetails(user);
    			return userDetails;
    		}
    		return null;
    }
}
