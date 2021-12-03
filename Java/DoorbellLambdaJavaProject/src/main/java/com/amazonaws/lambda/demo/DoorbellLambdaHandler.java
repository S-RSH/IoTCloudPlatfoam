package com.amazonaws.lambda.demo;

import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DoorbellLambdaHandler implements RequestHandler<Object, String> {

	private DynamoDB dynamoDb;

    @Override
    public String handleRequest(Object input, Context context) {
        this.initDynamoDbClient(); // DynamoDBClient 초기화

        Table table = dynamoDb.getTable("doorbell"); // doorbell이라는 이름을 가진 dynamoDB의 테이블 얻어오기

        
        ScanSpec scanSpec = new ScanSpec(); // DynamoDB를 스캔하기 위한 객체 생성

        ItemCollection<ScanOutcome> items=null; // 얻어올 item들을 저장 할 itemCollection을 생성
        try {
            items = table.scan(scanSpec); // 얻어온 테이블을 스캔하여 itemCollection으로 저장
        }
        catch (Exception e) { // 에러 발생시 실행
            System.err.println("Unable to scan the table:");
            System.err.println(e.getMessage());
        }

        return getResponse(items); // itemCollection을 json형식을 본따 만들어진  String으로 변환
    }

    private String getResponse(ItemCollection<ScanOutcome> items) {

        Iterator<Item> iter = items.iterator(); // 얻어온 itemCollection을 반복자를 통해 각 Item으로 분리하기 위한 반복자 생성
        String response = "{ \"data\": ["; // json으로 만들 기본 양식(맨 앞에 추가)
        for (int i =0; iter.hasNext(); i++) { // 반복자를 더 이상 반복 할 내용이 없을 때 까지 반복
            if (i!=0) // 첫 번째 객체가 아닐 경우에만
                response +=","; // 해당 객체 앞에 ,추가
            response += iter.next().toJSON(); // 객체를 json 형식의 문자열로 변경
        }
        response += "]}"; // 모든 반복이 끝났을 경우 String 객체의 괄호를 닫는다
        return response; // String 객체 반환
    }

    private void initDynamoDbClient() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion("ap-northeast-2").build(); // 해당 리전의 DynamoDB 접근 *지역에 따라서 수정*

        this.dynamoDb = new DynamoDB(client); //이 class dynamoDb 객체를 얻어온 DynamoDB 객체로 저장 
    }
}