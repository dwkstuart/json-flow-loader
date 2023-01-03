package com.example.json.flow.loader;

import com.example.json.flow.loader.conditions.Visibilty;
import com.example.json.flow.loader.pages.ErrorPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class BeanServiceTest {

    @Autowired
    BeanService beanService;

    @Test
    void getErrorPageBean() {
        Object bean = beanService.getBean("ErrorPage");
        Assertions.assertTrue(bean instanceof ErrorPage);
    }

    @Test
    void getAllVisibilityBeans() {
        Map beans = beanService.getBeansByClass(Visibilty.class);
        Assertions.assertEquals(4, beans.size(), "Four possibly condition beans");
    }
}