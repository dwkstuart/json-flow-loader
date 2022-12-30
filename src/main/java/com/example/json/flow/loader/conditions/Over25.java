package com.example.json.flow.loader.conditions;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(value = "Over25")
@Data
public class Over25 implements Visibilty {

    private List<String> requiredFields = new ArrayList<>();

    @Override
    public Boolean isVisible(Map<String, String> requiredData) throws Exception {
        int age = Integer.valueOf(requiredData.get("age"));
        return age > 25;
    }

    @Override
    public List<String> getDataFieldNames() {
        return requiredFields;
    }

    @Override
    public void setRequiredFieldNames(List<String> requiredFieldNames) {
        requiredFields = requiredFieldNames;
    }
}
