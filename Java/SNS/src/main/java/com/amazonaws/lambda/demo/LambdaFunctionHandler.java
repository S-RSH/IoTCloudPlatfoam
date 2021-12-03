package com.amazonaws.lambda.demo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
    	final String AccessKey="CHANGETOYOUTACCESSKEY";
		final String SecretKey="CHANGETOYOURSECRETKEY";
		final String topicArn="CHANGETOYOURTOPICARN";
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(AccessKey, SecretKey);
		AmazonSNS sns = AmazonSNSClientBuilder.standard()
				.withRegion(Regions.AP_NORTHEAST_2)
				.withCredentials( new AWSStaticCredentialsProvider(awsCreds) )
				.build();
		
		final String msg = "{\n" + 
				"  \"default\": \"샘플 대체 메시지\",\n" + 
				"  \"GCM\": \"{ \\\"notification\\\": { \\\"title\\\": \\\"hihi\\\", \\\"body\\\": \\\"hi from aws\\\" }}\"\n" + 
				"}";
		
		PublishRequest publishRequest = new PublishRequest(topicArn, msg);
		PublishResult publishResponse = sns.publish(publishRequest);

		// 삭제 필요
        // TODO: implement your handler
        return "Hello from Lambda!";
    }

}
