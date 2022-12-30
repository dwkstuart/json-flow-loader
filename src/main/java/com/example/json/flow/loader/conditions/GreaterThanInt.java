package com.example.json.flow.loader.conditions;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(value = "GreaterThanInt")
public class GreaterThanInt implements Visibilty, DynamicVisibility {

    private List<String> requiredFields = new ArrayList<>();

    private String comparisonValue;

    private String fieldToCompare;

    @Override
    public Boolean isVisible(Map<String, String> requiredData) throws Exception {
        int  greaterThan =  Integer.valueOf(comparisonValue);
        int savedValue =  Integer.valueOf(requiredData.get(fieldToCompare));
        return savedValue > greaterThan;
    }

    @Override
    public List<String> getDataFieldNames() {
        return requiredFields;
    }

    @Override
    public void setRequiredFieldNames(List<String> requiredFieldNames) {
        this.requiredFields = requiredFieldNames;
    }

    @Override
    public void setComparisonValue(String comparisonValue) {
        this.comparisonValue = comparisonValue;
    }

    @Override
    public void setFieldToCompare(String fieldToCompare) {
        this.fieldToCompare = fieldToCompare;
    }
}
