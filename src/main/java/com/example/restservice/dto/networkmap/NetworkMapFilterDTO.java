package com.example.restservice.dto.networkmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NetworkMapFilterDTO {

    @NotNull
    @Field("pids")
    private List<String> pidsToFilterBy;

    @JsonCreator
    public NetworkMapFilterDTO(@JsonProperty(value="pids", required = true) List<String> pidsToFilterBy) {
        this.pidsToFilterBy = pidsToFilterBy;
    }

    public List<String> getPidsToFilterBy() {
        return pidsToFilterBy;
    }

    public void setPidsToFilterBy(List<String> pidsToFilterBy) {
        this.pidsToFilterBy = pidsToFilterBy;
    }
}
