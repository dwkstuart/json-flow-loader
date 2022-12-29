package com.example.json.flow.loader.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDefinition implements Serializable {


    private final static long serialVersionUID = 3764252012278698923L;
    private List<String> flows;
    private String productName;
    private String id;

//    public List<FlowDefinition> getFlowDefinition() {
//        return flowDefinitionList;
//    }
//
//    public void setFlowDefinition(List<FlowDefinition> flowDefinitionList) {
//        this.flowDefinitionList = flowDefinitionList;
//    }

}