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


//    @MessageMapping("/chat/send/algo")
//    @SendTo("/topic/algo")
//    public ChatVO sendMessageAlgo(@Payload ChatVO chatVO) {
//        return chatVO;
//    }
//
//    // 유저 추가
//    // Client에서 메세지를 보낼 경로를 지정. Client는 /chat.addUser 경로를 통해 서버로 메세지 전송가능.
//    @MessageMapping("/chat/addUser/algo")
//    // 받은 메세지를 보낼 장소를 정한다. Server는 받은 메세지를 참여자들에게 브로드캐스팅해준다.
//    @SendTo("/topic/algo") // pulbic 경로 설정 가능??
//    // @Payload를 사용하여 전송되는 데이터를 받는다.
//    public ChatVO addUserAlgo(@Payload ChatVO chatVO, SimpMessageHeaderAccessor headerAccessor) {
//        // 온 메세지를 기반으로 "username"을 Key값으로 설정하고, VO의 sender를 Value값으로 설정한다.
//        // 즉, 누가 보낸 것인지 정보를 담는다.
//        headerAccessor.getSessionAttributes().put("username", chatVO.getSender());
//        return chatVO;
//    } 

//    @MessageMapping("/chat/send/cs")
//    @SendTo("/topic/cs")
//    public ChatVO sendMessageCS(@Payload ChatVO chatVO) {
//        return chatVO;
//    }
//
//    // 유저 추가
//    @MessageMapping("/chat/addUser/cs")
//    @SendTo("/topic/cs")
//    public ChatVO addUserCS(@Payload ChatVO chatVO, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatVO.getSender());
//        return chatVO;
//    }
//
//    @MessageMapping("/chat/send/job")
//    @SendTo("/topic/job")
//    public ChatVO sendMessage2(@Payload ChatVO chatVO) {
//        return chatVO;
//    }
//
//    // 유저 추가
//    @MessageMapping("/chat/addUser/job")
//    @SendTo("/topic/job")
//    public ChatVO addUser2(@Payload ChatVO chatVO, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatVO.getSender());
//        return chatVO;
//    }
}