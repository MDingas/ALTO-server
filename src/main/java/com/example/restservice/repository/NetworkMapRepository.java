package com.example.restservice.repository;

import com.example.restservice.entity.NetworkMapEntity;

import java.util.List;
import java.util.Optional;

public interface NetworkMapRepository extends ALTORepository<NetworkMapEntity> {
    Optional<NetworkMapEntity> findVersionOfResource(String resourceId, String versionTag, List<String> pids);
    Optional<NetworkMapEntity> findLatestVersionOfResource(String resourceId, List<String> pids);
}
