package com.example.json.flow.loader.pages;

import com.example.json.flow.loader.model.Page;
import org.springframework.stereotype.Component;

@Component(value = "ErrorPage")
public class ErrorPage implements Page {

    private final String pageId = "Error";
    private final String pageTitle = "Error Page";
}
