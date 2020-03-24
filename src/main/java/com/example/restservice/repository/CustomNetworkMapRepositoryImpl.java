package com.example.restservice.repository;

import com.example.restservice.dto.networkmap.NetworkMapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomNetworkMapRepositoryImpl implements CustomNetworkMapRepository {

    MongoTemplate mongoTemplate;

    @Autowired
    public CustomNetworkMapRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<NetworkMapDTO> findByResourceIdAndTag(String resourceId, String tag) {
        Query query = new Query();
        query.addCriteria(Criteria.where("meta.vtag.resource-id").is(resourceId)
                .and("meta.vtag.tag").is(tag));

        NetworkMapDTO networkMapDTO = mongoTemplate.findOne(query, NetworkMapDTO.class);

        return Optional.ofNullable(networkMapDTO);
    }

    @Override
    public Optional<NetworkMapDTO> findLatestVersionByResourceId(String resourceId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("meta.vtag.resource-id").is(resourceId));
        query.with(Sort.by(Sort.Direction.DESC, "meta.vtag.tag"));
        query.limit(1);

        NetworkMapDTO networkMapDTO = mongoTemplate.find(query, NetworkMapDTO.class).get(0);

        return Optional.ofNullable(networkMapDTO);
    }


    @Override
    public Optional<NetworkMapDTO> findByResourceIdAndTagProjectSrcPIDs(String resourceId, String version, List<String> srcPIDs) {
        Query query = new Query();
        query.addCriteria(Criteria.where("meta.vtag.resource-id").is(resourceId)
                                  .and("meta.vtag.tag").is(version));

        for (String srcPID : srcPIDs) {
           query.fields().include("network-map." + srcPID);
        }
        query.fields().include("meta");

        NetworkMapDTO networkMapDTO = mongoTemplate.findOne(query, NetworkMapDTO.class);

        return Optional.ofNullable(networkMapDTO);
    }

    @Override
    public Optional<NetworkMapDTO> findLatestVersionByResourceIdProjectSrcPIDs(String resourceId, List<String> srcPIDs) {
        Query query = new Query();
        query.addCriteria(Criteria.where("meta.vtag.resource-id").is(resourceId));
        query.with(Sort.by(Sort.Direction.DESC, "meta.vtag.tag"));
        query.limit(1);

        for (String srcPID : srcPIDs) {
            query.fields().include("network-map." + srcPID);
        }
        query.fields().include("meta");

        NetworkMapDTO networkMapDTO = mongoTemplate.findOne(query, NetworkMapDTO.class);

        return Optional.ofNullable(networkMapDTO);
    }
}
