package com.example.restservice.entity.networkmap;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MetaInfoEntity {
    private String resourceId;

    @JsonCreator
    public MetaInfoEntity(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
