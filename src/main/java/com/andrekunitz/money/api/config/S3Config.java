package com.andrekunitz.money.api.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.andrekunitz.money.api.config.property.MoneyApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Autowired
    private MoneyApiProperty property;

    public AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(
                property.getS3().getAccessKeyId(),
                property.getS3().getSecretAccessKey());
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        return  amazonS3;
    }
}
