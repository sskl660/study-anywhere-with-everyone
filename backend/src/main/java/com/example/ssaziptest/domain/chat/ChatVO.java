package com.example.ssaziptest.domain.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok 사용. getter, setter... 자동 정의.
@Data
// 모든 파라미터 생성자 자동 생성.
@AllArgsConstructor
// 모든 파라미터가 없는 생성자 자동 생성.
@NoArgsConstructor
// 채팅 정보를 담아서 전달할 VO. VO는 Read Only의 성격을 갖는다.
public class ChatVO {

//    현재 상태를 저장한다(입장, 퇴장, 채팅 중)
//    private MessageType type;
    // 채팅 방
    private String roomId;
    // 채팅 내용
    private String content;
    // 보낸 사람
    private String sender;
    // 보낸 이메일
    private String senderId;
}
