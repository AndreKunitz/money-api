package com.andrekunitz.money.api.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.Tag;
import com.amazonaws.services.s3.model.lifecycle.LifecycleFilter;
import com.amazonaws.services.s3.model.lifecycle.LifecycleTagPredicate;
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
        if (!amazonS3.doesBucketExistV2(property.getS3().getBucket())) {
            amazonS3.createBucket(new CreateBucketRequest(property.getS3().getBucket()));
            BucketLifecycleConfiguration.Rule regraExpiracao = new BucketLifecycleConfiguration.Rule()
                    .withId("Regra de expiração de arquivos temporários")
                    .withFilter(new LifecycleFilter(new LifecycleTagPredicate(new Tag("expirar", "true"))))
                    .withStatus(BucketLifecycleConfiguration.ENABLED);
            BucketLifecycleConfiguration configuration = new BucketLifecycleConfiguration()
                    .withRules(regraExpiracao);
            amazonS3.setBucketLifecycleConfiguration(property.getS3().getBucket(), configuration);
        }
        return  amazonS3;
    }
}
