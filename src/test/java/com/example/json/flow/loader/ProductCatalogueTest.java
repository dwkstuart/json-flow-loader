package com.example.json.flow.loader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductCatalogueTest {

    @Test
    void onApplicationEvent() throws IOException {

        Assertions.assertTrue(!ProductCatalogue.getProductMap().isEmpty(), "Product not empty");
        Assertions.assertTrue(!ProductCatalogue.getFlowMap().isEmpty(), "Product not empty");
    }


}