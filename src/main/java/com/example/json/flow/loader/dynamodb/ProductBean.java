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
public class ProductBean {

    public final static String TABLE_NAME = "JSON_STORE";
    private String productName;
    private String productionDefinition;
    private String pk;
    private String sk;

    @SuppressWarnings("SameReturnValue")
    @DynamoDbPartitionKey
    public String getPk() {
        return "PRODUCT_ITEM";
    }

    @SuppressWarnings("EmptyMethod")
    public void setPk(String pk) {

    }

    @DynamoDbSortKey
    public String getSk() {
        return "NAME#" + productName;
    }

    @SuppressWarnings("EmptyMethod")
    public void setSk(String pk) {
        //Intentionally left blank PK is static
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductionDefinition() {
        return productionDefinition;
    }

    public void setProductionDefinition(String productionDefinition) {
        this.productionDefinition = productionDefinition;
    }


}
