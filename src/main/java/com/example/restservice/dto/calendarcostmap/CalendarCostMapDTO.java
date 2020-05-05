package com.example.restservice.dto.calendarcostmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "CalendarCostMaps")
public class CalendarCostMapDTO {

    @JsonIgnore
    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Field("meta")
    private MetaDataDTO metaDataDTO;

    @NotNull
    @Field("cost-map")
    private Map<String, Map<String, List<Integer>>> costMappings;

    @JsonCreator
    public CalendarCostMapDTO(@JsonProperty(value = "meta", required = true) MetaDataDTO metaDataDTO,
                              @JsonProperty(value = "cost-map", required = true) Map<String, Map<String, List<Integer>>> costMappings) {
        this.metaDataDTO = metaDataDTO;
        this.costMappings = costMappings;
    }

    public MetaDataDTO getMetaDataDTO() {
        return metaDataDTO;
    }

    public void setMetaDataDTO(MetaDataDTO metaDataDTO) {
        this.metaDataDTO = metaDataDTO;
        }

    public Map<String, Map<String, List<Integer>>> getCostMappings() {
        return costMappings;
    }

    public void setCostMappings(Map<String, Map<String, List<Integer>>> costMappings) {
        this.costMappings = costMappings;
        }
}
