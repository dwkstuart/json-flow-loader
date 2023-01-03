package com.example.json.flow.loader.dtos;

import com.example.json.flow.loader.BeanService;
import com.example.json.flow.loader.FileLoader;
import com.example.json.flow.loader.builder.ProductBuilder;
import com.example.json.flow.loader.config.DynamoDBIntegrationTestConfiguration;
import com.example.json.flow.loader.dynamodb.DynamoDBIntegration;
import com.example.json.flow.loader.dynamodb.ProductBean;
import com.example.json.flow.loader.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductDefinitionTest {
    @Autowired
    BeanService beanService;
    String jsonInput;

    DynamoDbTable<ProductBean> productBeanDynamoDbTable;
    @Autowired
    DynamoDBIntegrationTestConfiguration dynamoDBIntegrationTestConfiguration;
    @Autowired
    DynamoDBIntegration dynamoDBIntegration;

    @BeforeEach
    public void init() {
        dynamoDBIntegrationTestConfiguration.dynamoDBLocalSetup();
        productBeanDynamoDbTable = dynamoDBIntegration.getTable(ProductBean.TABLE_NAME, ProductBean.class);
        populateDynamoDB();
    }

    @Test
    void getFlowDefinition() {
        ProductBean productBean;
        productBean = ProductBean.builder().productName("test_product").build();
        ProductBean fetched = productBeanDynamoDbTable.getItem(productBean);

        ProductBuilder productBuilder = new ProductBuilder(beanService);
        Product product;
        product = productBuilder.buildProduct(fetched.getProductionDefinition());
        Map beans = beanService.getBeansByClass(Product.class);
        assertEquals(1, beans.size());
        assertNotNull(product);
        productBeanDynamoDbTable.deleteItem(fetched);
    }

    private void populateDynamoDB() {
        ProductBean productBean;
        try {
            String productDefinitionJson = FileLoader.getJsonString("test_product.json");
            productBean = ProductBean.builder().productName("test_product").productionDefinition(productDefinitionJson).build();
            productBeanDynamoDbTable.putItem(productBean);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}