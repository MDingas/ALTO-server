package com.example.restservice.service.networkmap;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.entity.networkmap.NetworkMapEntity;
import com.example.restservice.mapper.NetworkMapMapper;
import com.example.restservice.repository.networkmap.NetworkMapRepository;
import com.example.restservice.service.ALTOResourceGenericRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NetworkMapServiceImpl extends ALTOResourceGenericRepoService<NetworkMapEntity,
                                                                          NetworkMapDTO,
                                                                          NetworkMapRepository,
                                                                          NetworkMapMapper>
                                   implements NetworkMapService {

    @Autowired
    public NetworkMapServiceImpl(NetworkMapRepository networkMapRepository, NetworkMapMapper networkMapMapper) {
        super(networkMapRepository, networkMapMapper);
    }

    @Override
    public Optional<NetworkMapDTO> getResourceWithFilter(String resourceId, String version, NetworkMapFilterDTO networkMapFilterDTO) {
        List<String> srcPIDs = networkMapFilterDTO.getPidsToFilterBy();

        if (srcPIDs.isEmpty()) {
            return this.getResource(resourceId, version);
        } else {
            Optional<NetworkMapEntity> optionalNetworkMapEntity = resourceRepository.findVersionOfResource(resourceId, version, srcPIDs);

            return optionalNetworkMapEntity.map(networkMapEntity -> resourceMapper.mapVersionAtPosition(networkMapEntity, 0));
        }
    }

    @Override
    public Optional<NetworkMapDTO> getLatestResourceWithFilter(String resourceId, NetworkMapFilterDTO networkMapFilterDTO) {
        List<String> srcPIDs = networkMapFilterDTO.getPidsToFilterBy();

        if (srcPIDs.isEmpty()) {
            return this.getLatestResource(resourceId);
        } else {
            Optional<NetworkMapEntity> optionalNetworkMapEntity = resourceRepository.findLatestVersionOfResource(resourceId, srcPIDs);

            return optionalNetworkMapEntity.map(networkMapEntity -> resourceMapper.mapVersionAtPosition(networkMapEntity, 0));
        }
    }
}
