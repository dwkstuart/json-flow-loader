package com.example.json.flow.loader.conditions;

import com.example.json.flow.loader.model.Visibilty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(value = "True")
public class DefaultTrue implements Visibilty {

    private List<String> requiredFields = new ArrayList<>();

    @Override
    public Boolean isVisible(Map<String, String> requiredData) throws Exception {
        return Boolean.TRUE;
    }

    @Override
    public List<String> getDataFieldNames() {
        return requiredFields;
    }

    @Override
    public void setRequiredFieldNames(List<String> requiredFieldNames) {
        this.requiredFields = requiredFieldNames;
    }
}
