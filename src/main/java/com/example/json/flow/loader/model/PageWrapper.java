package com.example.json.flow.loader.model;

import com.example.json.flow.loader.conditions.Visibilty;
import lombok.Data;

@Data
public class PageWrapper {

    private String instanceId;
    private Page page;
    private Visibilty visibilty;
}
