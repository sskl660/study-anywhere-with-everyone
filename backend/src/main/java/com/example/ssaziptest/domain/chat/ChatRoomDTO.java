package com.example.ssaziptest.domain.chat;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatRoomDTO {
    private String roomId;
    private String name;
    // WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션을 의미한다.
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoomDTO create(String name){
        ChatRoomDTO room = new ChatRoomDTO();

        room.roomId = UUID.randomUUID().toString();
        room.name = name;
        return room;
    }
}
