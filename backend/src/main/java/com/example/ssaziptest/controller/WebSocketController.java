package com.example.ssaziptest.controller;

import com.example.ssaziptest.domain.chat.ChatVO;
import com.example.ssaziptest.domain.chat.ParticipantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    // 특정 브로커에게 메세지를 전달
    private final SimpMessagingTemplate template;
    // 채팅 참여 리스트
    private List<ParticipantDTO> participants = new ArrayList<ParticipantDTO>();
    String[] randEnter = {"님 어서오세요!", "님이 랜딩했습니다.", " 공주님 등장!", "님이 도킹했습니다!", "님 두두두둥장!", " 선수 입장~!", "님 하위~", "님! 삼성 가즈아~!", "님 싸하~!"};
    String[] randExit = {"님 안녕히가세요!", "님이 떠났습니다.", " 공주님 퇴장!", "님이 우주로 떠났습니다..", "님 퇴퇴퇴퇴장!", " 선수 퇴장~", "님 바위~", "님! 다음에 또 오세요~!", "님 싸빠~!"};


    // 접속, 재접속시에는 현재 참자가를 지우고 다시 참가자 리스트를 보낸다.
    @MessageMapping("/chat/enter")
    public void enter(ParticipantDTO part) {
        participants.remove(part);
        participants.add(part);
        template.convertAndSend("/topic/part", participants);
        ChatVO enterMessage = new ChatVO();
        enterMessage.setSenderId("입장");
        enterMessage.setContent(part.getPartName() + randEnter[(int)(Math.random() * randEnter.length)]);
        template.convertAndSend("/topic/chat/algo", enterMessage);
    }

    // 퇴장 시에는 현재 참가자를 제거한다.
    @MessageMapping("/chat/exit")
    public void exit(ParticipantDTO part) {
        participants.remove(part);
        template.convertAndSend("/topic/part", participants);
        ChatVO exitMessage = new ChatVO();
        exitMessage.setSenderId("퇴장");
        exitMessage.setContent(part.getPartName() + randExit[(int)(Math.random()  * randExit.length)]);
        template.convertAndSend("/topic/chat/algo", exitMessage);
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
}