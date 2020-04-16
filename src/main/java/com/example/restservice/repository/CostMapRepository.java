package com.example.restservice.repository;

import com.example.restservice.entity.CostMapEntity;

import java.util.List;
import java.util.Optional;

public interface CostMapRepository extends ALTORepository<CostMapEntity> {
    Optional<CostMapEntity> findVersionOfResource(String resourceId, String versionTag, List<String> srcPIDs, List<String> dstPIDs, String costMode, String costMetric);
    Optional<CostMapEntity> findLatestVersionOfResource(String resourceId, List<String> srcPIDs, List<String> dstPIDs, String costMode, String costMetric);
}
