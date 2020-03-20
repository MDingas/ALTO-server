package com.example.restservice.service;

import com.example.restservice.dto.NetworkMapDTO;

import java.util.List;

public interface Service {
    List<NetworkMapDTO> getAllNetworkMaps();
    NetworkMapDTO getNetworkMap(String resourceId, String version);
    NetworkMapDTO getLatestNetworkMap(String resourceId);
    void putNetworkMapDTO(NetworkMapDTO networkMapDTO);
}
