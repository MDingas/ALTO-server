package com.example.restservice.dto.networkmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

public class MetaDataDTO {

    @NotNull
    @JsonProperty("vtag")
    @Field("vtag")
    private VersionTagDTO versionTagDTO;

    public MetaDataDTO() {

    }

    public MetaDataDTO(VersionTagDTO versionTagDTO) {
        this.versionTagDTO = versionTagDTO;
    }

    public VersionTagDTO getVersionTagDTO() {
        return this.versionTagDTO;
    }

    public void setVersionTagDTO(VersionTagDTO versionTagDTO) {
        this.versionTagDTO = versionTagDTO;
    }
}
