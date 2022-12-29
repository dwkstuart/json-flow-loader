package com.example.json.flow.loader.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Product {

    private String productName;
    private String productId;
    private List<PageFlow> pageFlowList;
}
