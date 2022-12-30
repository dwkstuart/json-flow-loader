package com.example.json.flow.loader.conditions;

import java.util.List;
import java.util.Map;

public interface Visibilty {

    Boolean isVisible(Map<String, String> requiredData) throws Exception;

    List<String> getDataFieldNames();

    void setRequiredFieldNames(List<String> requiredFieldNames);


}
