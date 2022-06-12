package dev.mrkresnofatih.africanpenguin.configurations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsDynamoDbConfigs {
    @Value("${aws.accessKeyId}")
    private String awsDynamoDbAccessKey;

    @Value("${aws.secretKey}")
    private String awsDynamoDbAccessSecret;

    @Bean
    AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(awsDynamoDbAccessKey, awsDynamoDbAccessSecret)))
                .withRegion(Regions.AP_SOUTHEAST_1)
                .build();
    }
}
