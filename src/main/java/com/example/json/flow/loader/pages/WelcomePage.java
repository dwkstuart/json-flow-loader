package com.example.json.flow.loader.pages;

import com.example.json.flow.loader.model.Page;
import org.springframework.stereotype.Component;

@Component(value = "WelcomePage")

public class WelcomePage implements Page {

    private String pageId = "Welcome";
    private String pageTitle = "Welcome Page";

}
