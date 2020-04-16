package com.example.restservice.mapper;

import com.example.restservice.entity.ALTOResourceEntity;

import java.util.List;
import java.util.Optional;

public interface ALTOMapper<T extends ALTOResourceEntity, S> {
    List<S> mapAllVersionsFrom(T t);
    Optional<S> mapFirstVersion(T t);
}
