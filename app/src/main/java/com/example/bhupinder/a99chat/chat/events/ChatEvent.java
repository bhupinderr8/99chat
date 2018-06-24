package com.example.bhupinder.a99chat.chat.events;


import com.example.bhupinder.a99chat.chat.entities.ChatMessage;

public class ChatEvent {
    ChatMessage msg;

    public ChatEvent(ChatMessage msg) {
        this.msg = msg;
    }

    public ChatMessage getMessage() {
        return msg;
    }
}
