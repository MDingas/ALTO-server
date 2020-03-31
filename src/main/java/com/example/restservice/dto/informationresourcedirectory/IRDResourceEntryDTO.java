package com.example.restservice.dto.informationresourcedirectory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class IRDResourceEntryDTO {

    @NotNull
    @Pattern(regexp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    @Field("uri")
    private String uri;

    @NotNull
    @Field("media-type")
    private ResourceMediaTypeDTO resourceMediaTypeDTO;

    @Field("accepts")
    private ParameterMediaTypeDTO parameterMediaTypeDTO;

    @Field("capabilities")
    private CapabilitiesDTO capabilitiesDTO;

    @Field("uses")
    private List<String> usedResources;

    @JsonCreator
    public IRDResourceEntryDTO(@JsonProperty(value = "uri", required = true) String uri,
                               @JsonProperty(value = "media-type", required = true) ResourceMediaTypeDTO mediaType,
                               @JsonProperty(value = "accepts", required = false) ParameterMediaTypeDTO parameterMediaTypeDTO,
                               @JsonProperty(value = "capabilities", required = false) CapabilitiesDTO capabilitiesDTO,
                               @JsonProperty(value = "uses", required = false) List<String> usedResources) {
        this.uri = uri;
        this.resourceMediaTypeDTO = mediaType;
        this.parameterMediaTypeDTO = parameterMediaTypeDTO;
        this.capabilitiesDTO = capabilitiesDTO;
        this.usedResources = usedResources;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ResourceMediaTypeDTO getResourceMediaTypeDTO() {
        return resourceMediaTypeDTO;
    }

    public void setResourceMediaTypeDTO(ResourceMediaTypeDTO resourceMediaTypeDTO) {
        this.resourceMediaTypeDTO = resourceMediaTypeDTO;
    }

    public ParameterMediaTypeDTO getParameterMediaTypeDTO() {
        return parameterMediaTypeDTO;
    }

    public void setParameterMediaTypeDTO(ParameterMediaTypeDTO parameterMediaTypeDTO) {
        this.parameterMediaTypeDTO = parameterMediaTypeDTO;
    }

    public CapabilitiesDTO getCapabilitiesDTO() {
        return capabilitiesDTO;
    }

    public void setCapabilitiesDTO(CapabilitiesDTO capabilitiesDTO) {
        this.capabilitiesDTO = capabilitiesDTO;
    }

    public List<String> getUsedResources() {
        return usedResources;
    }

    public void setUsedResources(List<String> usedResources) {
        this.usedResources = usedResources;
        }
}
