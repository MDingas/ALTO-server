package com.example.restservice.dto.networkmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

public class MetaDataDTO {

    @NotNull
    @Field("vtag")
    private VersionTagDTO versionTagDTO;

    @JsonCreator
    public MetaDataDTO(@JsonProperty(value = "vtag", required = true) VersionTagDTO versionTagDTO) {
        this.versionTagDTO = versionTagDTO;
    }

    public VersionTagDTO getVersionTagDTO() {
        return this.versionTagDTO;
    }

    public void setVersionTagDTO(VersionTagDTO versionTagDTO) {
        this.versionTagDTO = versionTagDTO;
    }
}
