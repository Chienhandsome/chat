package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
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
            textLichSu = textLichSu + "\n" +textNhap;
            lichSuChat.setText(textLichSu);
            textNhap = "";
            nhapText.setText(textNhap);
        });
    }
}