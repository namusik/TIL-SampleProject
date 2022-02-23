package com.sample.stompkafka.dto;

import com.sample.stompkafka.model.ChatMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    public enum MessageType {
        ENTER, TALK
    }
    private ChatMessage.MessageType type;
    //채팅방 ID
    private String roomId;
    //보내는 사람
    private String sender;
    //내용
    private String message;
}
