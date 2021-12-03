package com.example.android_resapi;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;


public class firebasedoorbell extends FirebaseMessagingService {// FirebaseMessagingService를 이용할 때 필요한 클래스
        private static final String TAG = "MyFirebaseMsgService";

        @Override
        public void onNewToken(@NonNull String token) {// 새 토큰 생성시 실행
            Log.d(TAG, "Refreshed token: " + token);
            //sendRegistrationToServer(token);
        }
}
