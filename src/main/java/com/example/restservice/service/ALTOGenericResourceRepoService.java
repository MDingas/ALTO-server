package com.example.restservice.service;

import com.example.restservice.entity.ALTOResourceEntity;
import com.example.restservice.mapper.ALTOResourceMapper;
import com.example.restservice.repository.ALTOResourceRepository;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ALTOGenericResourceRepoService<ALTOResourceEntityType extends ALTOResourceEntity,
                                            ALTOProjectionType,
                                            ALTOResourceDTOType,
                                            ALTOResourceRepositoryType extends ALTOResourceRepository<ALTOResourceEntityType, ALTOProjectionType>,
                                            ALTOResourceMapperType extends ALTOResourceMapper<ALTOResourceEntityType, ALTOResourceDTOType>> {

    protected ALTOResourceRepositoryType resourceRepository;
    protected ALTOResourceMapperType resourceMapper;


    public ALTOGenericResourceRepoService(ALTOResourceRepositoryType resourceRepository,
                                          ALTOResourceMapperType resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
    }

    public List<ALTOResourceDTOType> getAllResources() {
        List<ALTOResourceDTOType> resourceDTOs = new ArrayList<>();

        List<ALTOResourceEntityType> resourceEntities = resourceRepository.findAll();

        for (ALTOResourceEntityType resourceEntity : resourceEntities) {
            List<ALTOResourceDTOType> versionsOfresourceDTO = resourceMapper.mapAllVersions(resourceEntity);
            resourceDTOs.addAll(versionsOfresourceDTO);
        }

        return resourceDTOs;
    }

    public Optional<ALTOResourceDTOType> getResource(String resourceId, String resourceTag) throws NotFoundException  {
        Optional<ALTOResourceEntityType> optionalResourceEntity = resourceRepository.findVersionOfResource(resourceId, resourceTag);
        return optionalResourceEntity.map(altoResourceEntityType -> resourceMapper.mapVersionAtPosition(altoResourceEntityType, 0));
    }

    public Optional<ALTOResourceDTOType> getLatestResource(String resourceId) {
        Optional<ALTOResourceEntityType> optionalResourceEntity = resourceRepository.findLatestVersionOfResource(resourceId);
        return optionalResourceEntity.map(altoResourceEntityType -> resourceMapper.mapVersionAtPosition(altoResourceEntityType, 0));
    }
}
