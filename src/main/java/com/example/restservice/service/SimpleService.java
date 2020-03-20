package com.example.restservice.service;

import com.example.restservice.dto.NetworkMapDTO;
import com.example.restservice.repository.NetworkMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleService implements Service {

    private NetworkMapRepository networkMapRepository;

    @Autowired
    public SimpleService(NetworkMapRepository networkMapRepository) {
        this.networkMapRepository = networkMapRepository;
    }

    @Override
    public List<NetworkMapDTO> getAllNetworkMaps() {
        return networkMapRepository.findAll();
    }

    @Override
    public NetworkMapDTO getNetworkMap(String resourceId, String version) {
        return null;
    }

    @Override
    public NetworkMapDTO getLatestNetworkMap(String resourceId) {
        return null;
    }

    @Override
    public void putNetworkMapDTO(NetworkMapDTO networkMapDTO) {
        networkMapRepository.insert(networkMapDTO);
    }
}
