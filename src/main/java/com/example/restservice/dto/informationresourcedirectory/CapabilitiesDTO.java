package com.example.restservice.dto.informationresourcedirectory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class CapabilitiesDTO {

    @Field("cost-type-names")
    private List<String> costTypeNames;

    @Field("cost-constrains")
    private boolean hasCostConstrains;

    @Field("prop-types")
    private List<String> propertyTypes;

    @JsonCreator
    public CapabilitiesDTO(@JsonProperty(value = "cost-type-names", required = false) List<String> costTypeNames,
                           @JsonProperty(value = "cost-constrains", required = false) boolean hasCostConstrains,
                           @JsonProperty(value = "prop-types", required = false) List<String> propertyTypes) {
        this.costTypeNames = costTypeNames;
        this.hasCostConstrains = hasCostConstrains;
        this.propertyTypes = propertyTypes;
    }

    public List<String> getCostTypeNames() {
        return costTypeNames;
    }

    public void setCostTypeNames(List<String> costTypeNames) {
        this.costTypeNames = costTypeNames;
    }

    public boolean isHasCostConstrains() {
        return hasCostConstrains;
    }

    public void setHasCostConstrains(boolean hasCostConstrains) {
        this.hasCostConstrains = hasCostConstrains;
    }

    public List<String> getPropertyTypes() {
        return propertyTypes;
    }

    public void setPropertyTypes(List<String> propertyTypes) {
        this.propertyTypes = propertyTypes;
    }
}
