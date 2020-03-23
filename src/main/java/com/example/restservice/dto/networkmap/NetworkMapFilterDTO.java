package com.example.restservice.dto.networkmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NetworkMapFilterDTO {

    @NotNull
    @JsonProperty("pids")
    @Field("pids")
    List<String> pidsToFilterBy;

    public NetworkMapFilterDTO() {
    }

    public NetworkMapFilterDTO(@NotNull List<String> pidsToFilterBy) {
        this.pidsToFilterBy = pidsToFilterBy;
    }

    public List<String> getsrcPIDs() {
        return pidsToFilterBy;
    }

    public void setPidsToFilterBy(List<String> pidsToFilterBy) {
        this.pidsToFilterBy = pidsToFilterBy;
    }
}
