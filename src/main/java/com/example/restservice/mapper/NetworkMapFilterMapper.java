package com.example.restservice.mapper;

import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.repository.networkmap.NetworkMapProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.restservice.repository.networkmap.NetworkMapProjection.NetworkMapProjectionBuilder;

@Component
public class NetworkMapFilterMapper implements ALTOFilterMapper<NetworkMapFilterDTO, NetworkMapProjection> {

    private NetworkMapProjectionBuilder networkMapProjectionBuilder;

    @Autowired
    public NetworkMapFilterMapper(NetworkMapProjectionBuilder networkMapProjectionBuilder) {
        this.networkMapProjectionBuilder = networkMapProjectionBuilder;
    }

    @Override
    public NetworkMapProjection mapFrom(NetworkMapFilterDTO networkMapFilterDTO) {
        List<String> srcPIDs = networkMapFilterDTO.getPidsToFilterBy();

        if (!srcPIDs.isEmpty()) {
            networkMapProjectionBuilder.setSrcPIDs(srcPIDs);
        }

        NetworkMapProjection networkMapProjection = networkMapProjectionBuilder.build();

        networkMapProjectionBuilder.clear();

        return networkMapProjection;
    }
}
