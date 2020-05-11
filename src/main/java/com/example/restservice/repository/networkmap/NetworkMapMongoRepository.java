package com.example.restservice.repository.networkmap;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import com.example.restservice.entity.networkmap.NetworkMapEntity;
import com.example.restservice.repository.ALTOResourceMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NetworkMapMongoRepository extends ALTOResourceMongoRepository<NetworkMapEntity> implements NetworkMapRepository {

    private static String[] resourceSpecificFields = {"addressAggregationEntities"};

    @Autowired
    public NetworkMapMongoRepository(MongoTemplate mongoTemplate) {
        super(NetworkMapEntity.class, mongoTemplate);
    }

    private Optional<List<AggregationOperation>> givenSingleVersionOfResourceBuildProjectionAggregationOperations(NetworkMapProjection networkMapProjection) {
        if (networkMapProjection.getSrcPIDs().isPresent()) {
            return Optional.of(
                    Arrays.asList(
                        unwind("mappingEntity.addressAggregationEntities"),
                        match(Criteria.where("mappingEntity.addressAggregationEntities.pid").in(networkMapProjection.getSrcPIDs().get())),
                        group()
                            .first("metaInfoEntity").as("metaInfoEntity")
                            .first("mappingEntity.versionTag").as("versionTag")
                            .push("mappingEntity.addressAggregationEntities").as("addressAggregationEntities")
                    )
            );
        } else {
            return Optional.empty();
        }
    }

    private List<AggregationOperation> buildFindResourceAggregationOperations(String resourceId, String versionTag, NetworkMapProjection projection) {
        List<AggregationOperation> aggregationOperations = new ArrayList<>();

        if (versionTag != null) {
            aggregationOperations.addAll(buildGetVersionOfResourceAggregationOperations(resourceId, versionTag));
        } else {
            aggregationOperations.addAll(buildGetLatestVersionOfResourceAggregationOperations(resourceId));
        }

        Optional<List<AggregationOperation>> optionalPIDProjectionAggregations = givenSingleVersionOfResourceBuildProjectionAggregationOperations(projection);

        if (optionalPIDProjectionAggregations.isPresent()) {
            addAggregationOperationsWithProjection(aggregationOperations, optionalPIDProjectionAggregations.get(), resourceSpecificFields);
        } else {
            addAggregationOperationsWithoutProjections(aggregationOperations);
        }

        return aggregationOperations;
    }

    private List<AggregationOperation> buildFindResourceAggregationOperations(String resourceId, NetworkMapProjection projection) {
        return buildFindResourceAggregationOperations(resourceId, null, projection);
    }

    @Override
    public Optional<NetworkMapEntity> findVersionOfResourceWithProjection(String resourceId, String versionTag, NetworkMapProjection networkMapProjection) {
        List<AggregationOperation> aggregationOperations = buildFindResourceAggregationOperations(resourceId, versionTag, networkMapProjection);

        TypedAggregation<NetworkMapEntity> aggregation = newAggregation(NetworkMapEntity.class, aggregationOperations);

        NetworkMapEntity networkMapEntity = findResourceViaAggregation(aggregation);

        return Optional.ofNullable(networkMapEntity);
    }

    @Override
    public Optional<NetworkMapEntity> findLatestVersionOfResourceWithProjection(String resourceId, NetworkMapProjection networkMapProjection) {
        List<AggregationOperation> aggregationOperations = buildFindResourceAggregationOperations(resourceId, networkMapProjection);

        TypedAggregation<NetworkMapEntity> aggregation = newAggregation(NetworkMapEntity.class, aggregationOperations);

        NetworkMapEntity networkMapEntity = findResourceViaAggregation(aggregation);

        return Optional.ofNullable(networkMapEntity);
    }
}
