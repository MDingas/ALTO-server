package com.example.restservice.repository;

import com.example.restservice.dto.networkmap.NetworkMapDTO;

import java.util.List;
import java.util.Optional;

public interface CustomNetworkMapRepository {
    Optional<NetworkMapDTO> findByResourceIdAndTag(String resourceId, String tag);
    Optional<NetworkMapDTO> findLatestVersionByResourceId(String resourceId);
    Optional<NetworkMapDTO> findByResourceIdAndTagProjectSrcPIDs(String resourceId, String version, List<String> srcPIDs);
}
