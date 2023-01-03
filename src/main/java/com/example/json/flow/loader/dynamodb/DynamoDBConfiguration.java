package com.example.json.flow.loader.dynamodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoDBConfiguration {

    @Value("${amazon.region}")
    private String region;

    @Value("${dynamodb.endpoint.url}")
    private String dynamoDBEndpoint;

    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder().endpointOverride(URI.create(dynamoDBEndpoint)).region(Region.of(region)).build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient()).build();
    }

}
