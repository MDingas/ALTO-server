package com.example.restservice.dto.endpointpropertymap;

import com.example.restservice.dto.networkmap.MetaDataDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Document(collection = "EndpointPropertyMap")
public class EndpointPropertyMapDTO {

    @JsonIgnore
    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    @Field("meta")
    private MetaDataDTO metaDataDTO;

    @NotNull
    @Field("endpoint-properties")
    private Map<String, Map<String, String>> endpointPropertiesMap;
}
