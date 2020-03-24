package com.example.restservice.dto.costmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CostTypeDTO {

    @NotBlank
    @Field("cost-mode")
    private String costMode;

    @NotBlank
    @Field("cost-metric")
    private String costMetric;

    @Field("description")
    private String description;

    @JsonCreator
    public CostTypeDTO(@JsonProperty(value = "cost-mode", required = true) String costMode,
                       @JsonProperty(value = "cost-metric", required = true) String costMetric,
                       @JsonProperty(value = "description", required = false) String description) {
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
