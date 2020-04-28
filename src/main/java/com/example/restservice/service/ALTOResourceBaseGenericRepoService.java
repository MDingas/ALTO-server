package com.example.restservice.service;

import com.example.restservice.entity.ALTOResourceEntity;
import com.example.restservice.mapper.ALTOMapper;
import com.example.restservice.repository.ALTOResourceBaseRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ALTOResourceBaseGenericRepoService<T extends ALTOResourceEntity, S> implements ALTOResourceBaseService<S> {

    protected ALTOResourceBaseRepository<T> altoResourceBaseRepository;
    protected ALTOMapper<T, S> altoMapper;

    public ALTOResourceBaseGenericRepoService(ALTOResourceBaseRepository<T> altoResourceBaseRepository,
                                              ALTOMapper<T, S> altoMapper) {
        this.altoResourceBaseRepository = altoResourceBaseRepository;
        this.altoMapper = altoMapper;
    }

    @Override
    public List<S> getAll() {
        List<S> resourceDTOs = new ArrayList<>();

        List<T> resourceEntities = altoResourceBaseRepository.findAll();

        for (T resourceEntity : resourceEntities) {
            List<S> versionsOfresourceDTO = altoMapper.mapAllVersionsFrom(resourceEntity);
            resourceDTOs.addAll(versionsOfresourceDTO);
        }

        return resourceDTOs;
    }

    @Override
    public Optional<S> get(String resourceId, String resourceTag) {
        Optional<T> optionalResourceEntity = altoResourceBaseRepository.findVersionOfResource(resourceId, resourceTag);

        if (optionalResourceEntity.isPresent()) {
            return altoMapper.mapFirstVersion(optionalResourceEntity.get());
        } else {
            return Optional.empty();
        }
    }
}
