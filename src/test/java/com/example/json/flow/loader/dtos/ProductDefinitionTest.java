package com.example.json.flow.loader.dtos;

import com.example.json.flow.loader.BeanService;
import com.example.json.flow.loader.FileLoader;
import com.example.json.flow.loader.builder.ProductBuilder;
import com.example.json.flow.loader.model.Product;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductDefinitionTest {
    @Autowired
    BeanService beanService;
    String jsonInput;

    @BeforeEach
    void setUp() {
        try {
            jsonInput = FileLoader.getJsonString("test_product.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFlowDefinition() {
        Gson gson = new Gson();
        ProductDefinition test = gson.fromJson(jsonInput, ProductDefinition.class);

        ProductBuilder productBuilder = new ProductBuilder(beanService);
        Product product = null;
        try {
            product = productBuilder.buildProduct(jsonInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map beans = beanService.getBeansByClass(Product.class);
        assertNotNull(product);
    }
}