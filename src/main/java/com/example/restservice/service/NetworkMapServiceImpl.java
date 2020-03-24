package com.example.restservice.service;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;
import com.example.restservice.repository.NetworkMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Component
public class NetworkMapServiceImpl implements NetworkMapService {

    private NetworkMapRepository networkMapRepository;

    @Autowired
    public NetworkMapServiceImpl(NetworkMapRepository networkMapRepository) {
        this.networkMapRepository = networkMapRepository;
    }

    @Override
    public List<NetworkMapDTO> getAllNetworkMaps() {
        return networkMapRepository.findAll();
    }

    @Override
    public NetworkMapDTO getNetworkMap(String resourceId, String version) throws NotFoundException {
        return networkMapRepository.findByResourceIdAndTag(resourceId, version)
                                   .orElseThrow(()
                                           -> new NotFoundException(String.format("Could not find network map with id %s and version %s", resourceId, version)));
    }

    @Override
    public NetworkMapDTO getLatestNetworkMap(String resourceId) throws NotFoundException {
        return networkMapRepository.findLatestVersionByResourceId(resourceId)
                                   .orElseThrow(() ->
                                           new NotFoundException(String.format("Could not find any network map with id %s",resourceId)));
    }

    @Override
    public NetworkMapDTO getNetworkMap(String resourceId, String version, NetworkMapFilterDTO networkMapFilterDTO) throws NotFoundException {
        List<String> srcPIDs = networkMapFilterDTO.getsrcPIDs();
        return networkMapRepository.findByResourceIdAndTagProjectSrcPIDs(resourceId, version, srcPIDs)
                .orElseThrow(()
                        -> new NotFoundException(String.format("Could not find any network map with id %s and version %s", resourceId, version)));
    }

    @Override
    public NetworkMapDTO getLatestNetworkMap(String resourceId, NetworkMapFilterDTO networkMapFilterDTO) throws NotFoundException {
        List <String> srcPIDs = networkMapFilterDTO.getsrcPIDs();
        return networkMapRepository.findLatestVersionByResourceIdProjectSrcPIDs(resourceId, srcPIDs)
                .orElseThrow(()
                        -> new NotFoundException(String.format("Could not find any network map with id %s", resourceId)));
    }

    @Override
    public void putNetworkMapDTO(NetworkMapDTO networkMapDTO) {
        networkMapRepository.insert(networkMapDTO);
    }
}
