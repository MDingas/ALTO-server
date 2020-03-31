package com.example.restservice.dto.costmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CostTypeDTO {

    @NotNull
    @Field("cost-mode")
    private CostModeDTO costMode;

    @NotNull
    @Field("cost-metric")
    private CostMetricDTO costMetric;

    @Field("description")
    private String description;

    @JsonCreator
    public CostTypeDTO(@JsonProperty(value = "cost-mode", required = true) CostModeDTO costMode,
                       @JsonProperty(value = "cost-metric", required = true) CostMetricDTO costMetric,
                       @JsonProperty(value = "description", required = false) String description) {
        this.costMode = costMode;
        this.costMetric = costMetric;
        this.description = description;
    }

    public CostModeDTO getCostMode() {
        return costMode;
    }

    public void setCostMode(CostModeDTO costMode) {
        this.costMode = costMode;
    }

    public CostMetricDTO getCostMetric() {
        return costMetric;
    }

    public void setCostMetric(CostMetricDTO costMetric) {
        this.costMetric = costMetric;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
