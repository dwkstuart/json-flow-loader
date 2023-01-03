package com.example.json.flow.loader;

import com.example.json.flow.loader.builder.FlowBuilder;
import com.example.json.flow.loader.builder.ProductBuilder;
import com.example.json.flow.loader.dynamodb.DynamoDBIntegration;
import com.example.json.flow.loader.dynamodb.FlowBean;
import com.example.json.flow.loader.dynamodb.ProductBean;
import com.example.json.flow.loader.model.PageFlow;
import com.example.json.flow.loader.model.Product;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.exception.SdkClientException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "ProductCatalogue")
public class ProductCatalogue {

    private static final Map<String, Product> productMap = new HashMap<>();
    private static final Map<String, PageFlow> flowMap = new HashMap<>();


    final ProductBuilder productBuilder;
    final DynamoDBIntegration dynamoDBIntegration;
    final FlowBuilder flowBuilder;

    public ProductCatalogue(final ProductBuilder productBuilder,
                            final DynamoDBIntegration dynamoDBIntegration,
                            final FlowBuilder flowBuilder
    ) {
        this.productBuilder = productBuilder;
        this.dynamoDBIntegration = dynamoDBIntegration;
        this.flowBuilder = flowBuilder;


    }

    public static Product getProduct(String productName) {
        return productMap.get(productName);
    }

    public static Map<String, Product> getProductMap() {
        return productMap;
    }

    public static PageFlow getPageFlow(String flowName) {
        return flowMap.get(flowName);
    }

    public static Map<String, PageFlow> getFlowMap() {
        return flowMap;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) throws IOException {
        try {
            List<FlowBean> flowDefinitions = dynamoDBIntegration.getFlowItemsFromDB();
            if (flowDefinitions.isEmpty()) {
                loadFromResourcesIfDBDown();
            }

            for (FlowBean flowBean : flowDefinitions) {
                PageFlow pageFlow = flowBuilder.buildPageFlow(flowBean.getFlowDefinition());
                flowMap.put(flowBean.getFlowName(), pageFlow);
            }

            List<ProductBean> prodDefinitions = dynamoDBIntegration.getProductItemsFromDB();

            for (ProductBean productBean : prodDefinitions) {
                Product product = productBuilder.buildProduct(productBean.getProductionDefinition());
                productMap.put(product.getProductName(), product);
            }

        } catch (SdkClientException e) {
            System.out.println("Connection Error");
            loadFromResourcesIfDBDown();
        }


    }

    private void loadFromResourcesIfDBDown() throws IOException {

        List<String> flowNames = new ArrayList<>();
        flowNames.add("test_flow");
        flowNames.add("residency_flow");
        flowNames.add("residency_flow_V2");
        flowNames.add("welcome_flow");

        for (String flowName : flowNames) {
            try {
                String flowDefinitionJson = FileLoader.getJsonString("flows/" + flowName + ".json");
                PageFlow pageFlow = flowBuilder.buildPageFlow(flowDefinitionJson);
                flowMap.put(flowName, pageFlow);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        List<String> prodDefinitions = new ArrayList<>();
        prodDefinitions.add(FileLoader.getJsonString("ft_product.json"));
        prodDefinitions.add(FileLoader.getJsonString("pt_product.json"));

        for (String productJson : prodDefinitions) {
            Product product = productBuilder.buildProduct(productJson);
            productMap.put(product.getProductName(), product);
        }
    }

}
