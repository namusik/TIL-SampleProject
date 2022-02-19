package com.sample.stompkafka.service;

import com.sample.stompkafka.model.ChatRoom;
import com.sample.stompkafka.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        return chatRoomRepository.findAll();
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(Long roomId) {

        return chatRoomRepository.findById(roomId).orElse(null);
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = new ChatRoom(name);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }
}
