package com.example.restservice.dto.informationresourcedirectory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Document(collection = "InformationResourceDirectories")
public class InformationResourceDirectoryDTO {

    @JsonIgnore
    @Id
    private String id;

    @NotNull
    @Field("meta")
    private MetaDataDTO metaDataDTO;

    @NotNull
    @Field("resources")
    private Map<String, IRDResourceEntryDTO> resourceEntryDTOMap;

    @JsonCreator
    public InformationResourceDirectoryDTO(@JsonProperty(value = "meta", required = true) MetaDataDTO metaDataDTO,
                                           @JsonProperty(value = "resources", required = true) Map<String, IRDResourceEntryDTO> resourceEntryDTOMap) {
       this.metaDataDTO = metaDataDTO;
       this.resourceEntryDTOMap = resourceEntryDTOMap;
    }

    public MetaDataDTO getMetaDataDTO() {
        return metaDataDTO;
    }

    public void setMetaDataDTO(MetaDataDTO metaDataDTO) {
        this.metaDataDTO = metaDataDTO;
    }

    public Map<String, IRDResourceEntryDTO> getResourceEntryDTOMap() {
        return resourceEntryDTOMap;
    }

    public void setResourceEntryDTOMap(Map<String, IRDResourceEntryDTO> resourceEntryDTOMap) {
        this.resourceEntryDTOMap = resourceEntryDTOMap;
    }
}
