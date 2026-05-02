package com.sample.stompkafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column
    private String roomName;

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }
}
