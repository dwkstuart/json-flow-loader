package com.example.json.flow.loader;

import com.example.json.flow.loader.model.Visibilty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class BeanServiceTest {

    @Autowired
    BeanService beanService;

    @Test
    void getBean() {
        beanService.getBean("ErrorPage");
    }

    @Test
    void getAllVisiblityBeans() {
        Map beans = beanService.getBeansByClass(Visibilty.class);
        System.out.println(beans);
    }
}