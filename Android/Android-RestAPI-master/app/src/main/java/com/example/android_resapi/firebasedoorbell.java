package com.example.android_resapi;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.android_resapi.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class firebasedoorbell extends FirebaseMessagingService {
        private static final String TAG = "MyFirebaseMsgService";

        @Override
        public void onNewToken(@NonNull String token) {
            Log.d(TAG, "Refreshed token: " + token);
            //sendRegistrationToServer(token);
        }



}
