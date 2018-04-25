package com.javaworld.awslambda.widget.client;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public class WidgetS3Client {
    private static final BasicAWSCredentials credentials =
            new BasicAWSCredentials("", "");

    private static final String bucketName = "wiredbranch";

    public static void main(String[] args) throws URISyntaxException {
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        List<Bucket> buckets = s3client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }

        //upload file
        //bucket name , file name that will be present in directory, and file
        s3client.putObject(
                bucketName,
                "data.csv",
                new File( WidgetS3Client.class.getClass().getResource( "/data.csv" ).toURI())
                );


        S3Object s3object = s3client.getObject(bucketName, "13103509_10205911181841967_1635960045875559367_n.jpg");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        System.out.println(inputStream);
//        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/user/Desktop/hello.txt"));
    }
}
