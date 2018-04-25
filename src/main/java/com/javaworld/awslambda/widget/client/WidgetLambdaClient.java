package com.javaworld.awslambda.widget.client;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

public class WidgetLambdaClient {
    private static final BasicAWSCredentials credentials =
            new BasicAWSCredentials("", "");

    public static void main(String[] args) {
        // Setup credentials

        // Create an AWSLambda client
        AWSLambda lambda = AWSLambdaClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2)
                .build();


        // Create an InvokeRequest
        InvokeRequest request = new InvokeRequest()
                .withFunctionName("helloLambda")
                .withPayload("{ \"id\": \"2\"}");

        try {

            // Execute the InvokeRequest
            InvokeResult result = lambda.invoke(request);

            // We should validate the response
            System.out.println("Status Code: " + result.getStatusCode());

            // Get the response as JSON
            String json = new String(result.getPayload().array(), "UTF-8");

            // Show the response; we could use a library like Jackson to convert this to an object
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
    }
}
