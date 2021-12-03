package com.example.android_resapi.ui.apicall;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.android_resapi.R;
import com.example.android_resapi.httpconnection.GetRequest;
import com.example.android_resapi.ui.DeviceActivity;

public class GetThings extends GetRequest {
    final static String TAG = "APIGet"; // 로그를 찾기 위해 지정하는 태그
    String urlStr; // 전달된 REST API URL 주소가 저장될 문자열
    public GetThings(Activity activity, String urlStr) {// 클래스 외부에서 호출 할 메소드 정의

        super(activity); // 이 클래스가 상속받은 클래스(GetRequest)의 생성자에 이 클래스를 호출한 액티비티를 전달(이 프로젝트에서는 MainActivity)
        this.urlStr = urlStr; // 전달된 REST API URL 주소
    }

    @Override
    protected void onPreExecute() {// REST API를 통해 데이터가 들어오기 전
        try {
            url = new URL(urlStr); // 슈퍼 클래스의 접속할 API URL 지정

        } catch (MalformedURLException e) {// URL 접속에 실패 했을 경우
            Toast.makeText(activity,"URL is invalid:"+urlStr, Toast.LENGTH_SHORT).show(); // 토스트 메시지 출력
            activity.finish();
            e.printStackTrace();
        }
        TextView message = activity.findViewById(R.id.message); // 텍스트를 표시 할 TextView 지정
        message.setText("조회중..."); // 지정된 TextView에 텍스트 표시
    }

    @Override
    protected void onPostExecute(String jsonString) { // URL에서 REST API를 통해 JSON 객체를 받아오는데 성공했을 경우
        TextView message = activity.findViewById(R.id.message); // 텍스트를 변경 할 TextView 가져오기
        if (jsonString == null || jsonString.equals("")) {// 받아온 json이 없거나, 공백일 경우
            message.setText("사진 없음");// TextView에 찍힌 사진이 없다고 텍스트 변경
            return;
        }
        message.setText(""); // TextView를 비움
        ArrayList<Thing> arrayList = getArrayListFromJSONString(jsonString); // 얻어온 json객체를 Thing 객채로 지정된 ArrayList로 변경

        final ArrayAdapter adapter = new ArrayAdapter(activity,
                android.R.layout.simple_list_item_1,
                arrayList.toArray()); // ArrayAdapter를 통해 ArrayList를 (Thing으로 이루어진)Array로 변경
        ListView txtList = activity.findViewById(R.id.txtList); // ListView를 불러온다
        txtList.setAdapter(adapter); // 불러온 ListView에 생성된 ArrayAdapter를 지정
        txtList.setDividerHeight(10); // 각 항목 구분자 높이(세로길이) 조정

        txtList.setOnItemClickListener(new AdapterView.OnItemClickListener() {// ListView 항목을 클릭시 실행
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {// 항목을 클릭시
                Thing thing = (Thing)adapterView.getAdapter().getItem(i);// 해당 Item(Thing 객체)을 가져옴
                Intent intent = new Intent(activity, DeviceActivity.class ); // 새로 넘겨줄 Intent객체 생성 및 객체를 전달할 class(DeviceActivity) 지정
                intent.putExtra("ImageURL", thing.file); // Thing 객체의 file(사진이 저장된 주소)를 Intent 객체에 추가
                activity.startActivity(intent); // DeviceActivity를 ImageURL을 가지고 시작
            }
        });
    }

    protected ArrayList<Thing> getArrayListFromJSONString(String jsonString) {
        ArrayList<Thing> output = new ArrayList(); // Thing객체를 저장할 ArrayList 추가
        try {// json 객체를 처리할 수 있게 변환
            // 처음 double-quote와 마지막 double-quote 제거
            jsonString = jsonString.substring(1,jsonString.length()-1);
            // \\\" 를 \"로 치환
            jsonString = jsonString.replace("\\\"","\"");

            JSONObject root = new JSONObject(jsonString);// json 문자열을 json 객체로 변경
            JSONArray jsonArray = root.getJSONArray("data");// json 객체 내부의 array(data)를 따로 jsonArray 형식으로 저장

            for (int i = 0; i < jsonArray.length(); i++) {// jsonArray의 내부 요소(각 Thing 객체들을 json형식으로 변환한 것들)만큼 반복

                JSONObject jsonObject = (JSONObject)jsonArray.get(i);// jsonArray의 내부 요소를 다시 json객체 형식으로 저장

                Thing thing = new Thing(jsonObject.getString("file"), // json객체의 file 요소와
                        jsonObject.getString("time"));// time 요소를 가지고 Thing객체 생성

                output.add(thing);// 생성된 Thing객체를 ArrayList에 추가
            }

        } catch (JSONException e) {//json 오류 발생시 실행
            Log.e(TAG, "Exception in processing JSONString.", e);
            e.printStackTrace();
        }
        return output;
    }

    class Thing {
        String file; // 파일명
        String time; // 파일을 받아온 시간
        HashMap<String, String> tags;

        public Thing(String file, String time) {
            this.file = file;
            this.time = time;
            tags = new HashMap<String, String>();
        }

        public String toString() {
            return String.format("%s\n에 촬영된사진", time);
        } // 객체를 택스트 형식으로 변환 할 때 사용


    }
}

