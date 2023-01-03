package com.example.json.flow.loader.pages;

import com.example.json.flow.loader.model.Page;
import org.springframework.stereotype.Component;

@Component(value = "PageTwo")

public class PageTwo implements Page {

    private final String pageId = "Page2";
    private final String pageTitle = "Page2";

}
