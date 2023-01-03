package com.example.json.flow.loader.config;

import com.example.json.flow.loader.dynamodb.ProductBean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.CreateTableEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

@Configuration
public class DynamoDBIntegrationTestConfiguration {

    private final DynamoDbClient dynamoDbClient;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    public DynamoDBIntegrationTestConfiguration(DynamoDbClient dynamoDbClient, DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    public DynamoDbClient getDynamoDbClient() {
        return dynamoDbClient;
    }


    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return dynamoDbEnhancedClient;
    }

    public void dynamoDBLocalSetup() {
        try {
            ListTablesResponse tablesResult = dynamoDbClient.listTables();
            if (!tablesResult.tableNames().contains("JSON_STORE")) {
                CreateTableEnhancedRequest enhancedRequest = CreateTableEnhancedRequest.builder()
                        .build();
                dynamoDbEnhancedClient.table(ProductBean.TABLE_NAME, TableSchema.fromClass(ProductBean.class)).createTable(enhancedRequest);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public long getItemCount() {
        return dynamoDbClient.describeTable(DescribeTableRequest.builder().tableName(ProductBean.TABLE_NAME).build()).table().itemCount();
    }

}