package com.example.restservice.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import com.example.restservice.entity.NetworkMapEntity;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class NetworkMapMongoRepository extends ALTOMongoRepository<NetworkMapEntity> implements NetworkMapRepository {

    @Autowired
    public NetworkMapMongoRepository(MongoTemplate mongoTemplate) {
        super(NetworkMapEntity.class, mongoTemplate);
    }

    private List<AggregationOperation> buildProjectSrcPidsAndSingleVersion(List<String> srcPids) {
        return Arrays.asList(
                unwind("mappings.address-aggregations"),
                match(Criteria.where("mappings.address-aggregations.pid").in(srcPids)),
                group(getResourceProjectionFields())
                        .first("mappings.version-tag").as("version-tag")
                        .push("mappings.address-aggregations").as("address-aggregations"),
                group("_id._id")
                        .first("_id.resource-id").as("resource-id")
                        .push(new BasicDBObject("version-tag", "$version-tag")
                                .append("address-aggregations", "$address-aggregations")).as("mappings")
        );

    }

    public Optional<NetworkMapEntity> findVersionOfResource(String resourceId, String versionTag, List<String> srcPIDs) {
        List<AggregationOperation> operations = new ArrayList<>(buildVersionOfResourceAggregationOperationList(resourceId, versionTag));
        operations.addAll(buildProjectSrcPidsAndSingleVersion(srcPIDs));

        Aggregation aggregation = newAggregation(operations);

        AggregationResults<NetworkMapEntity> aggregationResults = mongoTemplate.aggregate(aggregation, "NetworkMaps", NetworkMapEntity.class);

        List<NetworkMapEntity> networkMapEntityList = aggregationResults.getMappedResults();

        if (networkMapEntityList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(networkMapEntityList.get(0));
        }
    }

    @Override
    public Optional<NetworkMapEntity> findLatestVersionOfResource(String resourceId, List<String> srcPIDs) {
        List<AggregationOperation> operations = new ArrayList<>(buildLatestVersionOfResourceAggregationOperationList(resourceId));
        operations.addAll(buildProjectSrcPidsAndSingleVersion(srcPIDs));

        Aggregation aggregation = newAggregation(operations);

        AggregationResults<NetworkMapEntity> aggregationResults = mongoTemplate.aggregate(aggregation, "NetworkMaps", NetworkMapEntity.class);

        List<NetworkMapEntity> networkMapEntityList = aggregationResults.getMappedResults();

        if (networkMapEntityList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(networkMapEntityList.get(0));
        }
    }
}
