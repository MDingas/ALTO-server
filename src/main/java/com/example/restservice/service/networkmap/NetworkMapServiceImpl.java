package com.example.restservice.service.networkmap;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.entity.networkmap.NetworkMapEntity;
import com.example.restservice.mapper.NetworkMapFilterMapper;
import com.example.restservice.mapper.NetworkMapMapper;
import com.example.restservice.repository.networkmap.NetworkMapProjection;
import com.example.restservice.repository.networkmap.NetworkMapRepository;
import com.example.restservice.service.ALTOGenericResourceRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class NetworkMapServiceImpl extends ALTOGenericResourceRepoService<NetworkMapEntity,
                                                                          NetworkMapProjection,
                                                                          NetworkMapDTO,
                                                                          NetworkMapRepository,
                                                                          NetworkMapMapper>
                                   implements NetworkMapService {

    private NetworkMapFilterMapper networkMapFilterMapper;

    @Autowired
    public NetworkMapServiceImpl(NetworkMapRepository networkMapRepository,
                                 NetworkMapMapper networkMapMapper,
                                 NetworkMapFilterMapper networkMapFilterMapper) {
        super(networkMapRepository, networkMapMapper);
        this.networkMapFilterMapper = networkMapFilterMapper;
    }

    @Override
    public Optional<NetworkMapDTO> getResourceWithFilter(String resourceId, String version, NetworkMapFilterDTO networkMapFilterDTO) {
        NetworkMapProjection networkMapProjection = networkMapFilterMapper.mapFrom(networkMapFilterDTO);
        Optional<NetworkMapEntity> optionalNetworkMapEntity = resourceRepository.findVersionOfResourceWithProjection(resourceId, version, networkMapProjection);
        return optionalNetworkMapEntity.map(networkMapEntity -> resourceMapper.mapVersionAtPosition(networkMapEntity, 0));
    }

    @Override
    public Optional<NetworkMapDTO> getLatestResourceWithFilter(String resourceId, NetworkMapFilterDTO networkMapFilterDTO) {
        NetworkMapProjection networkMapProjection = networkMapFilterMapper.mapFrom(networkMapFilterDTO);
        Optional<NetworkMapEntity> optionalNetworkMapEntity = resourceRepository.findLatestVersionOfResourceWithProjection(resourceId, networkMapProjection);
        return optionalNetworkMapEntity.map(networkMapEntity -> resourceMapper.mapVersionAtPosition(networkMapEntity, 0));
    }
}
