package com.example.restservice.service;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.entity.NetworkMapEntity;
import com.example.restservice.mapper.NetworkMapMapper;
import com.example.restservice.repository.NetworkMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NetworkMapServiceImpl implements NetworkMapService {

    private NetworkMapRepository networkMapRepository;
    private NetworkMapMapper networkMapMapper;

    @Autowired
    public NetworkMapServiceImpl(NetworkMapRepository networkMapRepository, NetworkMapMapper networkMapMapper) {
        this.networkMapRepository = networkMapRepository;
        this.networkMapMapper = networkMapMapper;
    }

    @Override
    public List<NetworkMapDTO> getAllNetworkMaps() {
        List<NetworkMapDTO> allNetworkMapDTOList = new ArrayList<>();

        List<NetworkMapEntity> networkMapEntityList = networkMapRepository.findAll();

        for (NetworkMapEntity networkMapEntity : networkMapEntityList) {
            List<NetworkMapDTO> versionsOfNetworkMapDTOList = networkMapMapper.mapAllVersionsFrom(networkMapEntity);
            allNetworkMapDTOList.addAll(versionsOfNetworkMapDTOList);
        }

        return allNetworkMapDTOList;
    }

    @Override
    public NetworkMapDTO getNetworkMap(String resourceId, String version) throws NotFoundException {
        NetworkMapEntity networkMapEntity = networkMapRepository.findVersionOfResource(resourceId, version)
                                            .orElseThrow(()
                                                -> new NotFoundException(String.format("Could not find network map with id %s and version %s", resourceId, version)));

        return networkMapMapper.mapFirstVersion(networkMapEntity)
                .orElseThrow(()
                        -> new NotFoundException(String.format("Could not map network map with id %s and version %s", resourceId, version)));
    }

    @Override
    public NetworkMapDTO getLatestNetworkMap(String resourceId) throws NotFoundException {
        NetworkMapEntity networkMapEntity = networkMapRepository.findLatestVersionOfResource(resourceId)
                .orElseThrow(()
                        -> new NotFoundException(String.format("Could not find any network map with id %s", resourceId)));

        if (networkMapEntity.getNetworkMappingsEntities().isEmpty()) {
            throw new NotFoundException(String.format("No versions of %s exist", resourceId));
        } else {
            return networkMapMapper.mapFirstVersion(networkMapEntity)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not map latest version of %s", resourceId)));
        }
    }

    @Override
    public NetworkMapDTO getNetworkMap(String resourceId, String version, NetworkMapFilterDTO networkMapFilterDTO) throws NotFoundException {
        List<String> srcPIDs = networkMapFilterDTO.getPidsToFilterBy();
        if (srcPIDs.isEmpty()) {
            return getNetworkMap(resourceId, version);
        } else {
            NetworkMapEntity networkMapEntity = networkMapRepository.findVersionOfResource(resourceId, version, srcPIDs)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not find network map with id %s and version %s", resourceId, version)));

            return networkMapMapper.mapFirstVersion(networkMapEntity)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not map network map with id %s and version %s", resourceId, version)));
        }
    }

    @Override
    public NetworkMapDTO getLatestNetworkMap(String resourceId, NetworkMapFilterDTO networkMapFilterDTO) throws NotFoundException {
        List <String> srcPIDs = networkMapFilterDTO.getPidsToFilterBy();
        if (srcPIDs.isEmpty()) {
            return getLatestNetworkMap(resourceId);
        } else {
            NetworkMapEntity networkMapEntity = networkMapRepository.findLatestVersionOfResource(resourceId, srcPIDs)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not find latest version of network map with id %s", resourceId)));

            return networkMapMapper.mapFirstVersion(networkMapEntity)
                    .orElseThrow(()
                            -> new NotFoundException(String.format("Could not map latest version of network map with id %s", resourceId)));
        }
    }

    //@Override
    //public void putNetworkMapDTO(NetworkMapDTO networkMapDTO) {
    //    networkMapRepository.insert(networkMapDTO);
    //}
}
