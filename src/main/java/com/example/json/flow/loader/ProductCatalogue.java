package com.example.json.flow.loader;

import com.example.json.flow.loader.builder.ProductBuilder;
import com.example.json.flow.loader.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "ProductCatalogue")
public class ProductCatalogue {

    private static Map<String, Product> productMap = new HashMap<>();
    @Autowired
    ProductBuilder productBuilder;

    public ProductCatalogue() {

    }

    public static Product getProduct(String productName) {
        return productMap.get(productName);
    }

    public static Map<String, Product> getProductMap() {
        return productMap;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) throws IOException {
        List<String> prodDefinitions = new ArrayList<>();

        prodDefinitions.add(FileLoader.getJsonString("ft_product.json"));
        prodDefinitions.add(FileLoader.getJsonString("pt_product.json"));
//        prodDefinitions.add(FileLoader.getJsonString("test_product.json"));

        for (String jsonString : prodDefinitions) {
            Product product = productBuilder.buildProduct(jsonString);
            productMap.put(product.getProductName(), product);
        }
    }
}
