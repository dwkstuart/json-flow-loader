package com.example.json.flow.loader.conditions;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(value = "Equality")
public class Equality implements Visibilty, DynamicVisibility {

    private List<String> requiredFields = new ArrayList<>();

    private String comparsionValue;

    private String fieldToCompare;

    @Override
    public Boolean isVisible(Map<String, String> requiredData) throws Exception {
        return comparsionValue.equals(requiredData.get(fieldToCompare));
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
    public void setComparsionValue(String comparsionValue) {
        this.comparsionValue = comparsionValue;
    }

    @Override
    public void setFieldToCompare(String fieldToCompare) {
        this.fieldToCompare = fieldToCompare;
    }
}
