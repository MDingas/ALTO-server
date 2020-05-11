package com.example.restservice.repository.costmap;

import com.example.restservice.entity.costmap.CostMapEntity;
import com.example.restservice.repository.ALTOResourceRepository;

import java.util.List;
import java.util.Optional;

public interface CostMapRepository extends ALTOResourceRepository<CostMapEntity, CostMapProjection> {
}
