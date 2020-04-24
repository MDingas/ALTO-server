package com.example.restservice.repository.networkmap;

import com.example.restservice.entity.NetworkMapEntity;
import com.example.restservice.repository.ALTOBaseRepository;

import java.util.List;
import java.util.Optional;

public interface NetworkMapRepository extends ALTOBaseRepository<NetworkMapEntity> {
    Optional<NetworkMapEntity> findVersionOfResource(String resourceId, String versionTag, List<String> pids);
    Optional<NetworkMapEntity> findLatestVersionOfResource(String resourceId, List<String> pids);
}
