package com.example.ssaziptest.controller.controllers;

import com.example.ssaziptest.domain.vo.ChatVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatVO sendMessage(@Payload ChatVO chatVO) {
        return chatVO;
    }

    // 유저 추가
    // Client에서 메세지를 보낼 경로를 지정. Client는 /chat.addUser 경로를 통해 서버로 메세지 전송가능.
    @MessageMapping("/chat.addUser")
    // 받은 메세지를 보낼 장소를 정한다. Server는 받은 메세지를 참여자들에게 브로드캐스팅해준다.
    @SendTo("/topic/public") // pulbic 경로 설정 가능??
    // @Payload를 사용하여 전송되는 데이터를 받는다.
    public ChatVO addUser(@Payload ChatVO chatVO, SimpMessageHeaderAccessor headerAccessor) {
        // 온 메세지를 기반으로 "username"을 Key값으로 설정하고, VO의 sender를 Value값으로 설정한다.
        // 즉, 누가 보낸 것인지 정보를 담는다.
        headerAccessor.getSessionAttributes().put("username", chatVO.getSender());
        return chatVO;
    }
}