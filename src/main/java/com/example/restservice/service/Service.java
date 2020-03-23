package com.example.restservice.service;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import com.example.restservice.dto.networkmap.NetworkMapFilterDTO;

import javax.ws.rs.NotFoundException;
import java.util.List;

public interface Service {
    List<NetworkMapDTO> getAllNetworkMaps();
    NetworkMapDTO getNetworkMap(String resourceId, String version) throws NotFoundException;
    NetworkMapDTO getLatestNetworkMap(String resourceId) throws NotFoundException;
    NetworkMapDTO getNetworkMap(String resourceId, String version, NetworkMapFilterDTO networkMapFilterDTO) throws NotFoundException;
    void putNetworkMapDTO(NetworkMapDTO networkMapDTO);
}
