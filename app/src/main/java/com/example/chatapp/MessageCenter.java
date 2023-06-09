package com.example.chatapp;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessageCenter {
    private DatabaseReference reference;
    private MessageReceiver messageReceiver;
    private final String TAG = "MessageCenter";

    public MessageCenter() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.reference = database.getReference("message");
    }

    public void sendMessageTo(String userID, String message){
        if(reference != null){
            DatabaseReference ref= reference.child(userID);
            ref.setValue(message);
        }
    }

    public void setMessageReceiver(String userID, MessageReceiver messageReceiver){
        this.messageReceiver = messageReceiver;
        getMessageFrom(userID);
    }

    private void getMessageFrom(String userID){
        if(reference != null){
            // Read from the database
            reference.child(userID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String message = dataSnapshot.getValue(String.class);
                    if(messageReceiver != null && message != null){
                        messageReceiver.onMessageReceived(userID, message);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });
        }
    }
}
