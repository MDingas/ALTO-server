package com.example.restservice.entity.informationresourcedirectory;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Optional;

public class CapabilityEntity {

    @Field("cost-constraints")
    private Boolean hasCostConstraints;

    @Field("cost-type-names")
    private List<String> costTypeNames;

    public CapabilityEntity(Boolean hasCostConstraints, List<String> costTypeNames) {
        this.hasCostConstraints = hasCostConstraints;
        this.costTypeNames = costTypeNames;
    }

    public Optional<Boolean> isHasCostConstraints() {
        return Optional.ofNullable(hasCostConstraints);
    }

    public void setHasCostConstraints(boolean hasCostConstraints) {
        this.hasCostConstraints = hasCostConstraints;
    }

    public Optional<List<String>> getCostTypeNames() {
        return Optional.ofNullable(costTypeNames);
    }

    public void setCostTypeNames(List<String> costTypeNames) {
        this.costTypeNames = costTypeNames;
    }
}
