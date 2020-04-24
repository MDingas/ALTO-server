package com.example.restservice.repository;

import com.example.restservice.entity.ALTOResourceEntity;

import java.util.List;
import java.util.Optional;

public interface ALTOBaseRepository<T extends ALTOResourceEntity> {
    Optional<T> findVersionOfResource(String id, String tag);
    Optional<T> findAllVersionsOfResource(String id);
    Optional<T> findLatestVersionOfResource(String id);
    List<T> findAll();
    void insertAll(List<T> t);
}

