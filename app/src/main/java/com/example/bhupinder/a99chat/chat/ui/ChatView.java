package com.example.bhupinder.a99chat.chat.ui;


import com.example.bhupinder.a99chat.chat.entities.ChatMessage;

public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
