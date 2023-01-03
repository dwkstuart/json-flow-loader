package com.example.json.flow.loader.pages;

import com.example.json.flow.loader.model.Page;
import org.springframework.stereotype.Component;

@Component(value = "WelcomePage")

public class WelcomePage implements Page {

    private final String pageId = "Welcome";
    private final String pageTitle = "Welcome Page";

}
