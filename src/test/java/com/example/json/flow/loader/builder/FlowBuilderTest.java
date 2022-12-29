package com.example.json.flow.loader.builder;

import com.example.json.flow.loader.BeanService;
import com.example.json.flow.loader.FileLoader;
import com.example.json.flow.loader.model.PageFlow;
import com.example.json.flow.loader.model.Visibilty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FlowBuilderTest {

    FlowBuilder test;

    String jsonInput;
    @Autowired
    BeanService beanService;

    @BeforeEach
    void setUp() {

        jsonInput = "{\n" +
                "  \"id\": 12345,\n" +
                "  \"name\": \"TestFlow\",\n" +
                "  \"pages\": [\n" +
                "    {\n" +
                "      \"pageTitle\": \"WelcomePage\",\n" +
                "      \"pageInstance\": \"1_WP\",\n" +
                "      \"visibilityName\": \"True\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"pageTitle\": \"PageTwo\",\n" +
                "      \"pageInstance\": \"1_PageTwo\",\n" +
                "      \"visibilityName\": \"Over25\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        test = new FlowBuilder(beanService);
    }

    @Test
    void getPageFlow() throws Exception {
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
    public void whenResourceAsStream_thenReadSuccessful() {
        String test = null;
        try {
            test = FileLoader.getJsonString("ft_flow.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(test);
//        InputStream resource = new ClassPathResource(
//                "ft_flow.json").getInputStream();
//        try ( BufferedReader reader = new BufferedReader(
//                new InputStreamReader(resource)) ) {
//            String employees = reader.lines()
//                    .collect(Collectors.joining("\n"));
//
//            assertEquals("Joe Employee,Jan Employee,James T. Employee", employees);
//        }
    }

}