package com.example.ssaziptest.domain.chat;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatParticipantDTO {
//    private List<Participant> party = new List<Participant>();
    private String roomId;
    private String name;
    // WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션을 의미한다.
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatParticipantDTO create(String name){
        ChatParticipantDTO room = new ChatParticipantDTO();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }
}
