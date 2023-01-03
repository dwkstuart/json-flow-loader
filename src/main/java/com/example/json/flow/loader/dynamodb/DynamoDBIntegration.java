package com.example.json.flow.loader.dynamodb;

import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class DynamoDBIntegration {

    private final DynamoDbClient dynamoDbClient;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public DynamoDBIntegration(DynamoDbClient dynamoDbClient, DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbClient = dynamoDbClient;
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    public DynamoDbClient getDynamoDbClient() {
        return dynamoDbClient;
    }


    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return dynamoDbEnhancedClient;
    }


    public DynamoDbTable getTable(String tableName, Class classDefinition) {
        return dynamoDbEnhancedClient.table(tableName, TableSchema.fromClass(classDefinition));

    }

    public List<FlowBean> getFlowItemsFromDB() throws SdkClientException {
        List<FlowBean> returnedFlows = new ArrayList<>();
        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue("FLOW_ITEM").build());
        DynamoDbTable dynamoDbTable = dynamoDbEnhancedClient.table(FlowBean.TABLE_NAME, TableSchema.fromClass(FlowBean.class));
        Iterator<FlowBean> item;
        item = dynamoDbTable.query(queryConditional).items().iterator();
        while (item.hasNext()) {
            returnedFlows.add(item.next());
        }
        return returnedFlows;

    }

    public List<ProductBean> getProductItemsFromDB() throws SdkClientException {
        List<ProductBean> productBeanList = new ArrayList<>();
        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue("PRODUCT_ITEM").build());
        DynamoDbTable dynamoDbTable = dynamoDbEnhancedClient.table(ProductBean.TABLE_NAME, TableSchema.fromClass(ProductBean.class));

        Iterator<ProductBean> item = dynamoDbTable.query(queryConditional).items().iterator();
        while (item.hasNext()) {
            ProductBean productBeanIterator = item.next();
            productBeanList.add(productBeanIterator);
        }

        return productBeanList;
    }


}
