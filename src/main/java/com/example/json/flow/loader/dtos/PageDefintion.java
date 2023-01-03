package com.example.json.flow.loader.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class PageDefintion implements Serializable {

    @Serial
    private final static long serialVersionUID = 6353102606032553665L;
    @SerializedName("pageTitle")
    @Expose
    private String pageTitle;
    @SerializedName("pageInstance")
    @Expose
    private String pageInstance;
    @SerializedName("visibilityName")
    @Expose
    private String visibilityName;
    @SerializedName("comparisonValue")
    @Expose
    private String comparisonValue;
    @SerializedName("fieldToCompare")
    @Expose
    private String fieldToCompare;
    @SerializedName("requiredFieldNames")
    @Expose
    private List<String> requiredFieldNames;

    public String getComparisonValue() {
        return comparisonValue;
    }

    public void setComparisonValue(String comparisonValue) {
        this.comparisonValue = comparisonValue;
    }

    public String getFieldToCompare() {
        return fieldToCompare;
    }

    public void setFieldToCompare(String fieldToCompare) {
        this.fieldToCompare = fieldToCompare;
    }

    public List<String> getRequiredFieldNames() {
        return requiredFieldNames;
    }

    public void setRequiredFieldNames(List<String> requiredFieldNames) {
        this.requiredFieldNames = requiredFieldNames;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageInstance() {
        return pageInstance;
    }

    public void setPageInstance(String pageInstance) {
        this.pageInstance = pageInstance;
    }

    public String getVisibilityName() {
        return visibilityName;
    }


    public void setVisibilityName(String visibilityName) {
        this.visibilityName = visibilityName;
    }

}

