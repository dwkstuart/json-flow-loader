package com.example.json.flow.loader.pages;

import com.example.json.flow.loader.model.Page;
import org.springframework.stereotype.Component;

@Component(value = "UK National")

public class UkNationalPage implements Page {

    private final String pageId = "Uk National";
    private final String pageTitle = "Are you a UK national";

}
