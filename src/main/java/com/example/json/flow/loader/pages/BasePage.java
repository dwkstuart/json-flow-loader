package com.example.json.flow.loader.pages;

import com.example.json.flow.loader.model.Page;
import lombok.Data;

@Data
public class BasePage implements Page {

    private String pageId;
    private String pageTitle;


}
