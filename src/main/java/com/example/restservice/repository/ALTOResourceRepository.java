package com.example.restservice.repository;

import com.example.restservice.entity.ALTOResourceEntity;

import java.util.List;
import java.util.Optional;

public interface ALTOResourceRepository<ALTOResourceEntityType extends ALTOResourceEntity> {
    Optional<ALTOResourceEntityType> findVersionOfResource(String id, String tag);
    Optional<ALTOResourceEntityType> findAllVersionsOfResource(String id);
    Optional<ALTOResourceEntityType> findLatestVersionOfResource(String id);
    List<ALTOResourceEntityType> findAll();
    void insertAll(List<ALTOResourceEntityType> t);
}

