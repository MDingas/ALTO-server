package com.example.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class VersionTagDTO {

    @NotBlank
    @JsonProperty("resource-id")
    private String resourceId;

    @NotBlank
    @JsonProperty("tag")
    private String tag;

    public VersionTagDTO() {

    }

    public VersionTagDTO(String resourceId, String tag) {
        this.resourceId = resourceId;
        this.tag = tag;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
