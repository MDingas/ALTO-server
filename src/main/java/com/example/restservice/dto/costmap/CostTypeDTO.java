package com.example.restservice.dto.costmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;

public class CostTypeDTO {

    @NotBlank
    @JsonProperty("cost-mode")
    @Field("cost-mode")
    private String costMode;

    @NotBlank
    @JsonProperty("cost-metric")
    @Field("cost-metric")
    private String costMetric;

    @JsonProperty("description")
    @Field("description")
    private String description;

    public CostTypeDTO() {

    }

    public CostTypeDTO(@NotBlank String costMode, @NotBlank String costMetric, String description) {
        this.costMode = costMode;
        this.costMetric = costMetric;
        this.description = description;
    }

    public String getCostMode() {
        return costMode;
    }

    public void setCostMode(String costMode) {
        this.costMode = costMode;
    }

    public String getCostMetric() {
        return costMetric;
    }

    public void setCostMetric(String costMetric) {
        this.costMetric = costMetric;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
