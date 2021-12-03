package com.example.android_resapi.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.android_resapi.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DeviceActivity extends AppCompatActivity {
    String urlStr; // Intent 객체를 통해 넘겨받을 이미지 주소
    ImageView camera; // 사진이 출력될 이미지뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        Intent intent = getIntent(); // Intent 객체를 가져온다.
        urlStr = intent.getStringExtra("ImageURL");// Intent 객체에서 저장된 주소를 받아온다.
        camera = findViewById(R.id.camera); // ImageView 지정

        new DownloadFilesTask().execute(urlStr); // 이미지를 저장하여 표시할 때 사용하는 코드
    }

    private class DownloadFilesTask extends AsyncTask<String,Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bmp = null; // Bitmap 이미지가 저장될 객체 생성
            try {
                String img_url = strings[0]; // 이미지의 URL 지정(해당 클래스를 호출될 때의 파라미터 urlStr의 위치는 0번이기 때문에 0번사용)
                URL url = new URL(img_url); // 얻은 String 객체를 바탕으로 URL 객체 생성
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream()); // URL객체를 통해 연결하여 이미지를 Bitmap 방식으로 저장
            } catch (MalformedURLException e) { // 오류 처리
                e.printStackTrace();
            } catch (IOException e) { // 오류 처리
                e.printStackTrace();
            }
            return bmp; // 저장된 Bitmap 객체 반환
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        } // 이미지를 받아오기 전 실행


        @Override
        protected void onPostExecute(Bitmap result) { // 이미지를 받아온 후 실행
            // doInBackground 에서 받아온 total 값 사용 장소
            camera.setImageBitmap(result); // 반환된 Bitmap 이미지 객체를 지정된 ImageView에 Bitmap 방식으로 등록
        }
    }

}


