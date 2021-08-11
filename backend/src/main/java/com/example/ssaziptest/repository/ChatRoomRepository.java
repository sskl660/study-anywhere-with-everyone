package com.example.ssaziptest.repository;

import com.example.ssaziptest.domain.chat.ChatParticipantDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatParticipantDTO> chatRoomDTOMap;

    @PostConstruct
    private void init() {
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    public List<ChatParticipantDTO> findAllRooms() {
        // 채팅방 생성 순서 최근 순으로 반환
        List<ChatParticipantDTO> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatParticipantDTO findRoomById(String id) {
        return chatRoomDTOMap.get(id);
    }

    public ChatParticipantDTO createChatRoomDTO(String name) {
        ChatParticipantDTO room = ChatParticipantDTO.create(name);
        chatRoomDTOMap.put(room.getRoomId(), room);

        return room;
    }
}