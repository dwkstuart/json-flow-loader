package com.example.json.flow.loader.builder;

import com.example.json.flow.loader.BeanService;
import com.example.json.flow.loader.FileLoader;
import com.example.json.flow.loader.conditions.Visibilty;
import com.example.json.flow.loader.model.PageFlow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FlowBuilderTest {

    FlowBuilder test;

    String jsonInput;
    @Autowired
    BeanService beanService;

    @BeforeEach
    void setUp() {

        try {
            jsonInput = FileLoader.getJsonString("flows/test_flow.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test = new FlowBuilder(beanService);
    }

    @Test
    void getPageFlow() {
        Assert.notNull(test.buildPageFlow(jsonInput), "Flow is not null");
    }

    @Test
    void welcomePageTrue() throws Exception {
        PageFlow pageFlow = test.buildPageFlow(jsonInput);
        Assert.isTrue(pageFlow.getPageWrapperList().get(0).getVisibilty().isVisible(null), "Welcome is true");
    }

    @Test
    void over25IsVisible() throws Exception {
        Map<String, String> requiredData = new HashMap<>();
        requiredData.put("age", "26");
        PageFlow pageFlow = test.buildPageFlow(jsonInput);
        assertEquals("TestFlow", pageFlow.getFlowName());
        Assert.isTrue(pageFlow.getPageWrapperList().get(1).getVisibilty().isVisible(requiredData), "Page Two is true");
    }

    @Test
    void under25notVisible() throws Exception {
        Map<String, String> dummyData = new HashMap<>();
        dummyData.put("age", "20");
        dummyData.put("course", "Maths");
        dummyData.put("credits", "15");
        dummyData.put("university", "University of Leeds");
        Map<String, String> requiredData = new HashMap<>();
        PageFlow pageFlow = test.buildPageFlow(jsonInput);
        Visibilty visibiltyCheck = pageFlow.getPageWrapperList().get(1).getVisibilty();
        List<String> requiredFields = visibiltyCheck.getDataFieldNames();
        for (String fieldName : requiredFields) {
            requiredData.put(fieldName, dummyData.get(fieldName));
        }

        Assert.isTrue(!visibiltyCheck.isVisible(requiredData), "Page Two is false");
    }

    @Test
    void equalityBuildTest() throws Exception {
        Map<String, String> dummyData = new HashMap<>();
        dummyData.put("UK_National", "Yes");
        Map<String, String> requiredData = new HashMap<>();
        PageFlow pageFlow = test.buildPageFlow(jsonInput);
        Visibilty visibiltyCheck = pageFlow.getPageWrapperList().get(2).getVisibilty();
        List<String> requiredFields = visibiltyCheck.getDataFieldNames();
        for (String fieldName : requiredFields) {
            requiredData.put(fieldName, dummyData.get(fieldName));
        }

        Assert.isTrue(visibiltyCheck.isVisible(requiredData), "Page Two is true");
    }

    @Test
    void under25notVisibleDynamic() throws Exception {
        Map<String, String> dummyData = new HashMap<>();
        dummyData.put("age", "20");
        dummyData.put("course", "Maths");
        dummyData.put("credits", "15");
        dummyData.put("university", "University of Leeds");
        Map<String, String> requiredData = new HashMap<>();
        PageFlow pageFlow = test.buildPageFlow(jsonInput);
        Visibilty visibiltyCheck = pageFlow.getPageWrapperList().get(3).getVisibilty();
        List<String> requiredFields = visibiltyCheck.getDataFieldNames();
        for (String fieldName : requiredFields) {
            requiredData.put(fieldName, dummyData.get(fieldName));
        }

        Assert.isTrue(!visibiltyCheck.isVisible(requiredData), "Page Two is false");
    }

}