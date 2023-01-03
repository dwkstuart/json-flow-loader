package com.example.json.flow.loader.builder;

import com.example.json.flow.loader.BeanService;
import com.example.json.flow.loader.ProductCatalogue;
import com.example.json.flow.loader.dtos.ProductDefinition;
import com.example.json.flow.loader.model.Page;
import com.example.json.flow.loader.model.PageFlow;
import com.example.json.flow.loader.model.Product;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductBuilder {



    private  BeanService beanService;

    public ProductBuilder(final BeanService beanService) {
        this.beanService = beanService;
    }

    public Product buildProduct(String jsonInput) {

        ProductDefinition components;

        Gson gson = new Gson();
        components = gson.fromJson(jsonInput, ProductDefinition.class);
        Product product = new Product();
        product.setProductId(components.getId());
        product.setPageFlowList(getPageFlows(components.getFlows()));
        product.setProductName(components.getProductName().replaceAll("\\s+", ""));
        return product;
    }

    private List<PageFlow> getPageFlows(List<String> pageFlowNames) {
        List<PageFlow> pageFlowList = new ArrayList<>();
        for (String flowName : pageFlowNames) {
            pageFlowList.add(ProductCatalogue.getPageFlow(flowName));
        }
        return pageFlowList;
    }

}
