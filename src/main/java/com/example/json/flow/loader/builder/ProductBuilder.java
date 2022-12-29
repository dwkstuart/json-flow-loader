package com.example.json.flow.loader.builder;

import com.example.json.flow.loader.BeanService;
import com.example.json.flow.loader.FileLoader;
import com.example.json.flow.loader.dtos.FlowDefinition;
import com.example.json.flow.loader.dtos.PageDefintion;
import com.example.json.flow.loader.dtos.ProductDefinition;
import com.example.json.flow.loader.model.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductBuilder {


    @Autowired
    private BeanService beanService;

    public ProductBuilder(BeanService beanService) {
        this.beanService = beanService;
    }

    public Product buildProduct(String jsonInput) throws IOException {

        ProductDefinition components;

        Gson gson = new Gson();
        components = gson.fromJson(jsonInput, ProductDefinition.class);
        List<FlowDefinition> flowDefinitionList = fetchFlowDefintions(components.getFlows());
        Product product = new Product();
        product.setProductId(components.getId());
        product.setPageFlowList(pageFlowList(flowDefinitionList));
        product.setProductName(components.getProductName().replaceAll("\\s+",""));
        return product;
    }

    private List<FlowDefinition> fetchFlowDefintions(List<String> flows) throws IOException {
        List<FlowDefinition> flowDefinitionList = new ArrayList<>();
        Gson gson = new Gson();
        for(String flow :  flows){
            String flowJson = FileLoader.getJsonString("flows/" + flow + ".json");
            FlowDefinition flowDefinition = gson.fromJson(flowJson, FlowDefinition.class);
            flowDefinitionList.add(flowDefinition);
        }
        return flowDefinitionList;
    }

    private List<PageFlow> pageFlowList(List<FlowDefinition> flowDefinition) {
        List<PageFlow> pageFlowList = new ArrayList<>();
        for (FlowDefinition flowInstance : flowDefinition) {
            PageFlow pageFlow;
            pageFlow = new PageFlow();
            pageFlow.setFlowName(flowInstance.getName());
            pageFlow.setPageWrapperList(buildPageListWithLogic(flowInstance));
            pageFlowList.add(pageFlow);
        }
        return pageFlowList;
    }

    private List<PageWrapper> buildPageListWithLogic(FlowDefinition flowDefinition) {
        List<PageWrapper> pageWrapperList = new ArrayList<>();
        for (PageDefintion pageDefintion : flowDefinition.getPages()) {
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setPage(getPageFromString(pageDefintion.getPageTitle()));
            Visibilty visibilty = getVisibityFromString(pageDefintion.getVisibilityName());
            visibilty.setRequiredFieldNames(new ArrayList<>(Arrays.asList("age")));
            pageWrapper.setVisibilty(visibilty);
            pageWrapperList.add(pageWrapper);
        }
        ;

        return pageWrapperList;
    }

    private Visibilty getVisibityFromString(String visibilityName) {
        return (Visibilty) beanService.getBean(visibilityName);
    }

    private Page getPageFromString(String pageTitle) {
        return (Page) beanService.getBean(pageTitle);
    }


}
