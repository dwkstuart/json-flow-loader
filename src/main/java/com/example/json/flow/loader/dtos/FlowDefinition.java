package com.example.json.flow.loader.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class FlowDefinition implements Serializable {


    private final static long serialVersionUID = -568420353426711513L;
    private String id;
    private String name;
    private List<PageDefintion> pages = null;

//

}
