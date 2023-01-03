package com.example.json.flow.loader.dynamodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@Data
@DynamoDbBean
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FlowBean {

    public final static String TABLE_NAME = "JSON_STORE";
    private String flowName;
    private String flowDefinition;
    private String pk;
    private String sk;

    @SuppressWarnings("SameReturnValue")
    @DynamoDbPartitionKey
    public String getPk() {
        return "FLOW_ITEM";
    }

    @SuppressWarnings("EmptyMethod")
    public void setPk(String pk) {
        //Intentionally left blank PK is static
    }

    @DynamoDbSortKey
    public String getSk() {
        return "NAME#" + flowName;
    }

    @SuppressWarnings("EmptyMethod")
    public void setSk(String pk) {
        //Intentionally left blank PK is static
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowDefinition() {
        return flowDefinition;
    }

    public void setFlowDefinition(String flowDefinition) {
        this.flowDefinition = flowDefinition;
    }
}
