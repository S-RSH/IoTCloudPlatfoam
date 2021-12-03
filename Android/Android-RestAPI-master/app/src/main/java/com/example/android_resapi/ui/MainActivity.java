package com.example.android_resapi.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import com.example.android_resapi.R;
import com.example.android_resapi.ui.apicall.GetThings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    final static String TAG = "Device_Token"; // 디바이스 토큰을 로그로 찾기위해 사용되는 태그

    @Override
    protected void onCreate(Bundle savedInstanceState) {// 앱 실행시 자동 실행하여 동작에 필요한 요소들을 생성
        FirebaseMessaging.getInstance().getToken() // 현재 토큰을 받아오는 코드 -> 토큰은 앱을 새로 설치 할 때마다 새롭게 생성 됨
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException()); // 토큰을 가져오는데 실패 했을 경우 출력되는 로그
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult(); // 토큰을 String 형식으로 저장

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token); // 토큰을 한눈에 알아보기 위해 다른 String과 결합
                        Log.d(TAG, msg); // 토큰 로그 출력 아래 Run 메뉴에서 Ctrl + F 후 Device_Token 검색
                    }
                });
        super.onCreate(savedInstanceState); // 앱 구동에 필요한 인스턴스 생성
        setContentView(R.layout.activity_main); // layout과 연결

        String url = "https://xxxxxxxxx.execute-api.ap-northeast-2.amazonaws.com/doorbell"; // 읽어올 REST API 주소 지정 *개인별로 다르니 수정*

        new GetThings(MainActivity.this, url).execute(); // REST API의 url주소를 전달하여 GetThings 클래스 실행


    }



}


