package com.example.restservice.mapper;

import com.example.restservice.entity.ALTOResourceEntity;

import java.util.List;

public interface ALTOResourceMapper<ALTOResourceEntityType extends ALTOResourceEntity, ALTOResourceDTOType> {
    List<ALTOResourceDTOType> mapAllVersions(ALTOResourceEntityType t);
    ALTOResourceDTOType mapVersionAtPosition(ALTOResourceEntityType t, int index) throws IndexOutOfBoundsException;
}
