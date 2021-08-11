package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.chat.ChatVO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    // 특정 브로커에게 메세지를 전달
    private final SimpMessagingTemplate template;

    // Client가 메세지를 Send할 수 있는 경로.
    // WebSocketConfig에서 설정한 prefix와 경로가 병합된다. 즉, /galaxy/chat...과 같이 Client는 경로를 설정해주어야한다.
    @MessageMapping("/chat/enter/algo")
    public void enter(ChatVO message) {
        message.setContent(message.getSender() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/topic/chat/algo" + message.getRoomId(), message);
    }

    // 해당 경로로 메세지 발행 요청!
    @MessageMapping("/chat/send/algo")
    public void message(ChatVO message) {
        // 해당 경로로 메세지 브로드 캐스팅!
        // 즉, 해당 경로는 채팅방을 구분하는 값이 된다!
        template.convertAndSend("/topic/chat/algo", message);
        // Client는 해당 주소를 SUBSCIBE하고 있다가 메세지를 화면에 출력!
    }
    ///chat/send/algo 받아서 /topic/chat/algo로 보내기!

    // 해당 경로로 메세지 발행 요청!
    @MessageMapping("/chat/send/cs")
    public void message2(ChatVO message) {
        // 해당 경로로 메세지 브로드 캐스팅!
        // 즉, 해당 경로는 채팅방을 구분하는 값이 된다!
        template.convertAndSend("/topic/chat/cs", message);
        // Client는 해당 주소를 SUBSCIBE하고 있다가 메세지를 화면에 출력!
    }
    // 해당 경로로 메세지 발행 요청!
    @MessageMapping("/chat/send/job")
    public void message3(ChatVO message) {
        // 해당 경로로 메세지 브로드 캐스팅!
        // 즉, 해당 경로는 채팅방을 구분하는 값이 된다!
        template.convertAndSend("/topic/chat/job", message);
        // Client는 해당 주소를 SUBSCIBE하고 있다가 메세지를 화면에 출력!
    }
}