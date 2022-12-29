package com.example.json.flow.loader.model;

import lombok.Data;

import java.util.List;

@Data
public class PageFlow {

    private String flowName;
    private List<PageWrapper> pageWrapperList;
}
