package com.example.json.flow.loader.builder;

import com.example.json.flow.loader.BeanService;
import com.example.json.flow.loader.conditions.DefaultTrue;
import com.example.json.flow.loader.conditions.DynamicVisibility;
import com.example.json.flow.loader.conditions.Equality;
import com.example.json.flow.loader.dtos.FlowDefinition;
import com.example.json.flow.loader.dtos.PageDefintion;
import com.example.json.flow.loader.model.Page;
import com.example.json.flow.loader.model.PageFlow;
import com.example.json.flow.loader.model.PageWrapper;
import com.example.json.flow.loader.conditions.Visibilty;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FlowBuilder {


    @Autowired
    private BeanService beanService;

    public FlowBuilder(BeanService beanService) {
        this.beanService = beanService;
    }

    public PageFlow buildPageFlow(String jsonInput) {
        FlowDefinition components;
        PageFlow pageFlow;
        Gson gson = new Gson();
        components = gson.fromJson(jsonInput, FlowDefinition.class);
        pageFlow = new PageFlow();
        pageFlow.setFlowName(components.getName());
        pageFlow.setPageWrapperList(buildPageListWithLogic(components));
        return pageFlow;
    }

    private List<PageWrapper> buildPageListWithLogic(FlowDefinition flowDefinition) {
        List<PageWrapper> pageWrapperList = new ArrayList<>();
        for (PageDefintion pageDefintion : flowDefinition.getPages()) {
            PageWrapper pageWrapper = new PageWrapper();
            pageWrapper.setPage(getPageFromString(pageDefintion.getPageTitle()));
            Visibilty visibilty = getVisibityFromString(pageDefintion.getVisibilityName());
            if(pageDefintion.getRequiredFieldNames() != null){
                visibilty.setRequiredFieldNames(pageDefintion.getRequiredFieldNames());
            }
            addDynamicFields(pageDefintion, visibilty);
            pageWrapper.setVisibilty(visibilty);
            pageWrapperList.add(pageWrapper);
        }


        return pageWrapperList;
    }

    private void addDynamicFields(PageDefintion pageDefintion, Visibilty visibilty){

        if(pageDefintion.getComparisonValue() != null && pageDefintion.getFieldToCompare() != null){
            DynamicVisibility dynamicVisibility = (DynamicVisibility) visibilty;
            dynamicVisibility.setFieldToCompare(pageDefintion.getFieldToCompare());
            dynamicVisibility.setComparisonValue(pageDefintion.getComparisonValue());
            visibilty = (Visibilty) dynamicVisibility;
        }

    }

    private Visibilty getVisibityFromString(String visibilityName) {
        return (Visibilty) beanService.getBean(visibilityName);
    }

    private Page getPageFromString(String pageTitle) {
        return (Page) beanService.getBean(pageTitle);
    }


}
