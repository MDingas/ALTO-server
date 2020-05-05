package com.example.restservice.entity.informationresourcedirectory;

import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CostTypeIRDEntry {

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("cost-mode")
    private String costMode;

    @NotNull
    @Field("cost-metric")
    private String costMetric;

    @Field("description")
    private String description;

    public CostTypeIRDEntry(String name, String costMode, String costMetric, String description) {
        this.name = name;
        this.costMode = costMode;
        this.costMetric = costMetric;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
