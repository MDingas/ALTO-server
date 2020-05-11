package com.example.restservice.repository;

import com.example.restservice.entity.ALTOResourceEntity;

import java.util.List;
import java.util.Optional;

public interface ALTOResourceRepository<ALTOResourceEntityType extends ALTOResourceEntity, ALTOResourceProjection> {
    Optional<ALTOResourceEntityType> findVersionOfResource(String id, String tag);
    Optional<ALTOResourceEntityType> findVersionOfResourceWithProjection(String id, String tag, ALTOResourceProjection projection);

    Optional<ALTOResourceEntityType> findLatestVersionOfResource(String id);
    Optional<ALTOResourceEntityType> findLatestVersionOfResourceWithProjection(String id, ALTOResourceProjection projection);

    List<ALTOResourceEntityType> findAll();

    void insert(ALTOResourceEntityType resource);
    void insertAll(List<ALTOResourceEntityType> resources);
}

