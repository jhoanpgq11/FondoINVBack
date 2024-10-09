package com.api.ejemplo.demo.config;

import java.net.URI;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sns.SnsClient;

import software.amazon.awssdk.regions.Region;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.api.ejemplo.demo.repository")
public class DynamoDB {
	 @Value("${amazon.dynamodb.endpoint}")
	    private String amazonDynamoDBEndpoint;

	    @Value("${aws.accessKeyId}")
	    private String awsAccessKeyId;

	    @Value("${aws.secretKey}")
	    private String awsSecretKey;

	    @Value("${aws.region}")
	    private String awsRegion;
	    @Bean
	    public AmazonDynamoDB amazonDynamoDB() {
	        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKeyId, awsSecretKey);
	        return AmazonDynamoDBClientBuilder.standard()
	                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, awsRegion))
	                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
	                .build();
	    }

	    @Bean
	    public SesClient sesClient() {
	        return SesClient.builder()
	                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKeyId, awsSecretKey)))
	                .endpointOverride(URI.create(amazonDynamoDBEndpoint)) 
	                .region(Region.of(awsRegion))
	                .build();
	    }

	    @Bean
	    public SnsClient snsClient() {
	        return SnsClient.builder()
	                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsAccessKeyId, awsSecretKey)))
	                .endpointOverride(URI.create(amazonDynamoDBEndpoint)) 
	                .region(Region.of(awsRegion))
	                .build();
	    }

}
