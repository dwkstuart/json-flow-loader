package com.example.json.flow.loader;

import com.example.json.flow.loader.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    @GetMapping(value = "/start/{productName}")
    public String getProduct(@PathVariable(value = "productName")
                             String productName) {
        Product product = ProductCatalogue.getProduct(productName);
        return product.getProductName() + ": " + product;
    }

    @GetMapping(value = "/productList")
    public String getProductList() {
        Map<String, Product> products = ProductCatalogue.getProductMap();

        return products.keySet().toString();
    }
}
