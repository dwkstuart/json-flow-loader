package com.example.json.flow.loader;

import com.example.json.flow.loader.config.DynamoDBIntegrationTestConfiguration;
import com.example.json.flow.loader.dynamodb.DynamoDBIntegration;
import com.example.json.flow.loader.dynamodb.FlowBean;
import com.example.json.flow.loader.dynamodb.ProductBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DynamoDbTests {

    @Autowired
    DynamoDBIntegrationTestConfiguration dynamoDBIntegrationTestConfiguration;
    @Autowired
    DynamoDBIntegration dynamoDBIntegration;

    @BeforeEach
    public void init() {
        dynamoDBIntegrationTestConfiguration.dynamoDBLocalSetup();
        cleanUpDB();
    }

    @Test
    public void addProduct() {
        DynamoDbTable<ProductBean> productBeanDynamoDbTable =
                dynamoDBIntegration.getTable(ProductBean.TABLE_NAME, ProductBean.class);
        ProductBean productBean;
        try {
            String productDefinitionJson = FileLoader.getJsonString("test_product.json");
            productBean = ProductBean.builder().productName("test_product").productionDefinition(productDefinitionJson).build();
            productBeanDynamoDbTable.putItem(productBean);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, dynamoDBIntegration.getProductItemsFromDB().size(), "One item added");
        productBeanDynamoDbTable.deleteItem(productBean);
        populateDB();
    }

    @Test
    public void addTestFlows() {
        DynamoDbTable<FlowBean> flowBeanDynamoDbTable = dynamoDBIntegration.getTable(FlowBean.TABLE_NAME, FlowBean.class);
        FlowBean flowBean;
        try {
            String flowDefinitionJson = FileLoader.getJsonString("flows/test_flow.json");
            flowBean = FlowBean.builder().flowName("test_flow").flowDefinition(flowDefinitionJson).build();
            flowBeanDynamoDbTable.putItem(flowBean);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(1, dynamoDBIntegration.getFlowItemsFromDB().size(), "One item added");
        flowBeanDynamoDbTable.deleteItem(flowBean);
        populateDB();
    }

    @Test
    public void fetchAllFlows() {
        setUpFlows();
        List<FlowBean> flows = dynamoDBIntegration.getFlowItemsFromDB();
        Assertions.assertEquals(4, flows.size());
        Assertions.assertEquals(4, dynamoDBIntegrationTestConfiguration.getItemCount());
        populateDB();
    }

    private void setUpFlows() {
        DynamoDbTable<FlowBean> flowBeanDynamoDbTable = dynamoDBIntegration.getTable(FlowBean.TABLE_NAME, FlowBean.class);
        List<String> flowNames = new ArrayList<>();
        flowNames.add("test_flow");
        flowNames.add("residency_flow");
        flowNames.add("residency_flow_V2");
        flowNames.add("welcome_flow");

        for (String flowName : flowNames) {
            FlowBean flowBean;
            try {
                String flowDefinitionJson = FileLoader.getJsonString("flows/" + flowName + ".json");
                flowBean = FlowBean.builder().flowName(flowName).flowDefinition(flowDefinitionJson).build();
                flowBeanDynamoDbTable.putItem(flowBean);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void setUpProducts() {
        DynamoDbTable<ProductBean> flowBeanDynamoDbTable = dynamoDBIntegration.getTable(ProductBean.TABLE_NAME, ProductBean.class);
        List<String> productNames = new ArrayList<>();
        productNames.add("ft_product");
        productNames.add("pt_product");
        productNames.add("test_product");


        for (String productName : productNames) {
            ProductBean productBean;
            try {
                String productJson = FileLoader.getJsonString(productName + ".json");
                productBean = ProductBean.builder().productName(productName).productionDefinition(productJson).build();
                flowBeanDynamoDbTable.putItem(productBean);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    private void populateDB() {
        setUpProducts();
        setUpFlows();
    }

    private void cleanUpDB() {
        DynamoDbTable<ProductBean> productBeanDynamoDbTable = dynamoDBIntegration.getTable(ProductBean.TABLE_NAME, ProductBean.class);
        DynamoDbTable<FlowBean> flowBeanDynamoDbTable = dynamoDBIntegration.getTable(FlowBean.TABLE_NAME, FlowBean.class);


        List<FlowBean> flows = dynamoDBIntegration.getFlowItemsFromDB();
        for (FlowBean flowBean : flows) {
            flowBeanDynamoDbTable.deleteItem(flowBean);
        }

        List<ProductBean> productItemsFromDB = dynamoDBIntegration.getProductItemsFromDB();
        for (ProductBean productBean : productItemsFromDB) {
            productBeanDynamoDbTable.deleteItem(productBean);
        }
    }
}
