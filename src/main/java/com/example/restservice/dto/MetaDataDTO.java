package com.example.restservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class MetaDataDTO {

    @NotNull
    @JsonProperty("vtag")
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
