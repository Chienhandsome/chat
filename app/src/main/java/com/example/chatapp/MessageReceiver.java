package com.example.chatapp;

public interface MessageReceiver {
    void onMessageReceived(String userID, String message);
}
