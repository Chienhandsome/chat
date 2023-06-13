package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public  class MainActivity extends AppCompatActivity
{
    MessageCenter messageCenter;
    final String partnerID = "Bimmm";
    final String myUserID = "Bemmm";
    TextView lichSuChat;
    TextView nhapText;
    Button nutSend;
    String textNhap="";
    String textLichSu="";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageCenter =  new MessageCenter();

        messageCenter.setMessageReceiver(myUserID, new MessageReceiver() {
            @Override
            public void onMessageReceived(String userID, String message) {
                //Nhận được tin nhắn mới
                runOnUiThread(() -> {
                    // gắn tin nhắn lên màn hình
                    textLichSu += "\n> "+userID+": "+message;
                    lichSuChat.setText(textLichSu);
                });
            }
        });

        lichSuChat = findViewById(R.id.lichsuchat);
        nhapText = findViewById(R.id.nhaptext);

        nutSend = findViewById(R.id.nutsend);

        nhapText.setOnClickListener(view ->
        {
            textNhap = nhapText.getText().toString();
            nhapText.setText(textNhap);
        });
        
        nutSend.setOnClickListener(view ->
        {
            textNhap = nhapText.getText().toString();
            // gửi tin nhắn cho partnerID
            messageCenter.sendMessageTo(partnerID, textNhap);

            textLichSu += "\n"+myUserID+": " +textNhap;
            lichSuChat.setText(textLichSu);
            textNhap = "";
            nhapText.setText(textNhap);
        });
    }
}