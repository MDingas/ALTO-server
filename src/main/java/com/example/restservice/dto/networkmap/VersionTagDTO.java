package com.example.restservice.dto.networkmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;

public class VersionTagDTO {

    @NotBlank
    @Field("resource-id")
    private String resourceId;

    @NotBlank
    @Field("tag")
    private String tag;

    @JsonCreator
    public VersionTagDTO(@JsonProperty(value = "resource-id", required = true) String resourceId,
                         @JsonProperty(value = "tag", required = true) String tag) {
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
