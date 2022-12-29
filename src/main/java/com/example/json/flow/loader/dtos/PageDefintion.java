package com.example.json.flow.loader.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PageDefintion implements Serializable {

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

