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

//        String jsonInput = "{\n" +
//                "  \"productName\": \"FullTime\",\n" +
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
//
//        String jsonInput2 = "{\n" +
//                "  \"productName\": \"PartTime\",\n" +
//                "  \"id\": 67890,\n" +
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


        prodDefinitions.add(FileLoader.getJsonString("ft_flow.json"));
        prodDefinitions.add(FileLoader.getJsonString("pt_flow.json"));

        for (String jsonString : prodDefinitions) {
            Product product = productBuilder.buildProduct(jsonString);
            productMap.put(product.getProductName(), product);
        }
    }
}
