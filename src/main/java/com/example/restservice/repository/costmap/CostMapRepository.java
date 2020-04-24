package com.example.restservice.repository.costmap;

import com.example.restservice.entity.CostMapEntity;
import com.example.restservice.repository.ALTOBaseRepository;

import java.util.List;
import java.util.Optional;

public interface CostMapRepository extends ALTOBaseRepository<CostMapEntity> {
    Optional<CostMapEntity> findVersionOfResource(String resourceId, String versionTag, List<String> srcPIDs, List<String> dstPIDs, String costMode, String costMetric);
    Optional<CostMapEntity> findLatestVersionOfResource(String resourceId, List<String> srcPIDs, List<String> dstPIDs, String costMode, String costMetric);
}