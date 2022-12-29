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
//        jsonInput = "{\n" +
//                "  \"productName\": \"Full Time\",\n" +
//                "  \"id\": 12345,\n" +
//                "  \"flows\": [\n" +
//                "    {\n" +
//                "      \"name\": \"Welcome\",\n" +
//                "      \"pages\": [\n" +
//                "        {\n" +
//                "          \"pageTitle\": \"WelcomePage\",\n" +
//                "          \"pageInstance\": \"1_WP\",\n" +
//                "          \"visibilityName\": \"True\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"pageTitle\": \"PageTwo\",\n" +
//                "          \"pageInstance\": \"1_PageTwo\",\n" +
//                "          \"visibilityName\": \"True\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"name\": \"Residency\",\n" +
//                "      \"pages\": [\n" +
//                "        {\n" +
//                "          \"pageTitle\": \"UK National\",\n" +
//                "          \"pageInstance\": \"1_UK_National\",\n" +
//                "          \"visibilityName\": \"True\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "          \"pageTitle\": \"PageTwo\",\n" +
//                "          \"pageInstance\": \"1_PageTwo\",\n" +
//                "          \"visibilityName\": \"Over25\"\n" +
//                "        }\n" +
//                "      ]\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";

        try {
            jsonInput = FileLoader.getJsonString("ft_flow.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFlowDefinition() {
        Gson gson = new Gson();
        gson.fromJson(jsonInput, ProductDefinition.class);

        ProductBuilder productBuilder = new ProductBuilder(beanService);
        Product product = productBuilder.buildProduct(jsonInput);
        Map beans = beanService.getBeansByClass(Product.class);
        assertNotNull(product);
    }
}