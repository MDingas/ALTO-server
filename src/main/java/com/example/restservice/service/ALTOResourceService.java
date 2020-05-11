package com.example.restservice.service;

import java.util.List;
import java.util.Optional;

public interface ALTOResourceService<ALTOResourceDTOType, ALTOResourceFilterType> {

    List<ALTOResourceDTOType> getAllResources();

    Optional<ALTOResourceDTOType> getResource(String resourceId, String resourceVersion);
    Optional<ALTOResourceDTOType> getResourceWithFilter(String resourceId, String resourceVersion, ALTOResourceFilterType filter);

    Optional<ALTOResourceDTOType> getLatestResource(String resourceId);
    Optional<ALTOResourceDTOType> getLatestResourceWithFilter(String resourceId, ALTOResourceFilterType filter);
}
