package com.example.restservice.repository.costmap;

import com.example.restservice.entity.costmap.CostMapEntity;
import com.example.restservice.repository.ALTOResourceMongoRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class CostMapMongoRepository extends ALTOResourceMongoRepository<CostMapEntity> implements CostMapRepository {

    private static String[] resourceSpecificFields = {"fromSrcCostEntities"};

    @Autowired
    public CostMapMongoRepository(MongoTemplate mongoTemplate) {
        super(CostMapEntity.class, mongoTemplate);
    }

    private Optional<MatchOperation> buildMatchCostTypeOperation(CostMapProjection costMapProjection) {
        Optional<String> optionalCostMode = costMapProjection.getCostMode();
        Optional<String> optionalCostMetric = costMapProjection.getCostMetric();

        if (optionalCostMode.isPresent() && optionalCostMetric.isPresent()) {
            return Optional.of(match(new Criteria().
                    andOperator(
                            Criteria.where("metaInfoEntity.costMode").is(optionalCostMode.get()),
                            Criteria.where("metaInfoEntity.costMetric").is(optionalCostMetric.get()))
            ));
        } else if (optionalCostMode.isPresent() && !optionalCostMetric.isPresent()) {
            return Optional.of(match(Criteria.where("metaInfoEntity.costMode").is(optionalCostMode.get())));

        } else if (!optionalCostMode.isPresent() && optionalCostMetric.isPresent()){
            return Optional.of(match(Criteria.where("metaInfoEntity.costMetric").is(optionalCostMetric.get())));
        } else {
            return Optional.empty();
        }
    }

    private Optional<List<AggregationOperation>> buildProjectionOfSrcAndDstPIDs(CostMapProjection costMapProjection) {
        Optional<List<String>> optionalSrcPIDs = costMapProjection.getSrcPIDs();
        Optional<List<String>> optionalDstPIDs = costMapProjection.getDstPIDs();

        if (!optionalSrcPIDs.isPresent() && !optionalDstPIDs.isPresent()) {
            return Optional.empty();
        } else {
            List<AggregationOperation> aggregationOperations = new ArrayList<>();

            aggregationOperations.add(unwind("mappingEntity.fromSrcCostEntities"));

            optionalSrcPIDs.ifPresent(srcPIDs -> aggregationOperations.add(match(Criteria.where("mappingEntity.fromSrcCostEntities.srcNode").in(srcPIDs))));

            aggregationOperations.add(unwind("mappingEntity.fromSrcCostEntities.dstCostEntities"));

            optionalDstPIDs.ifPresent(dstPIDs -> aggregationOperations.add(match(Criteria.where("mappingEntity.fromSrcCostEntities.dstCostEntities.dstNode").in(dstPIDs))));

            aggregationOperations.add(
                    group("mappingEntity.fromSrcCostEntities.srcNode")
                            .first("metaInfoEntity").as("metaInfoEntity")
                            .first("mappingEntity.versionTag").as("versionTag")
                            .push("mappingEntity.fromSrcCostEntities.dstCostEntities").as("dstCostEntities")
            );

            aggregationOperations.add(
                    project("metaInfoEntity", "versionTag")
                            .andExclude("_id")
                            .and("_id").as("srcNode")
                            .and("dstCostEntities").as("dstCostEntities")
            );

            aggregationOperations.add(
                    group()
                            .first("metaInfoEntity").as("metaInfoEntity")
                            .first("versionTag").as("versionTag")
                            .push(new BasicDBObject("srcNode", "$srcNode")
                                    .append("dstCostEntities", "$dstCostEntities"))
                                       .as("fromSrcCostEntities")
            );

            return Optional.of(aggregationOperations);
        }
    }

    private Optional<List<AggregationOperation>> givenSingleResourceBuildProjectionOfCostSrcAndDstAndSingleVersion(CostMapProjection costMapProjection) {
        boolean were_operations_added = false;

        List<AggregationOperation> operations = new ArrayList<>();

        Optional<MatchOperation> optionalMatchCostTypeOperation = buildMatchCostTypeOperation(costMapProjection);

        if(optionalMatchCostTypeOperation.isPresent()) {
            operations.add(optionalMatchCostTypeOperation.get());
            were_operations_added = true;
        }

        Optional<List<AggregationOperation>> optionalCostSrcDstProjectionOperations = buildProjectionOfSrcAndDstPIDs(costMapProjection);

        if (optionalCostSrcDstProjectionOperations.isPresent()) {
            operations.addAll(optionalCostSrcDstProjectionOperations.get());
            were_operations_added = true;
        }

        if (were_operations_added) {
            return Optional.of(operations);
        } else {
            return Optional.empty();
        }
    }

    private List<AggregationOperation> buildFindResourceAggregationOperations(String resourceId, String versionTag, CostMapProjection projection) {
        List<AggregationOperation> aggregationOperations = new ArrayList<>();

        if (versionTag != null) {
            aggregationOperations.addAll(buildGetVersionOfResourceAggregationOperations(resourceId, versionTag));
        } else {
            aggregationOperations.addAll(buildGetLatestVersionOfResourceAggregationOperations(resourceId));
        }

        Optional<List<AggregationOperation>> optionalProjectionAggregations = givenSingleResourceBuildProjectionOfCostSrcAndDstAndSingleVersion(projection);

        if (optionalProjectionAggregations.isPresent()) {
            addAggregationOperationsWithProjection(aggregationOperations, optionalProjectionAggregations.get(), resourceSpecificFields);
        } else {
            addAggregationOperationsWithoutProjections(aggregationOperations);
        }

        return aggregationOperations;
    }

    private List<AggregationOperation> buildFindResourceAggregationOperations(String resourceId, CostMapProjection projection) {
        return buildFindResourceAggregationOperations(resourceId, null, projection);
    }

    @Override
    public Optional<CostMapEntity> findVersionOfResourceWithProjection(String resourceId, String versionTag, CostMapProjection costMapProjection) {
        List<AggregationOperation> aggregationOperations = buildFindResourceAggregationOperations(resourceId, versionTag, costMapProjection);

        TypedAggregation<CostMapEntity> aggregation = newAggregation(CostMapEntity.class, aggregationOperations);

        CostMapEntity costMapEntity = findResourceViaAggregation(aggregation);

        return Optional.ofNullable(costMapEntity);
    }

    @Override
    public Optional<CostMapEntity> findLatestVersionOfResourceWithProjection(String resourceId, CostMapProjection costMapProjection) {
        List<AggregationOperation> aggregationOperations = buildFindResourceAggregationOperations(resourceId, costMapProjection);

        TypedAggregation<CostMapEntity> aggregation = newAggregation(CostMapEntity.class, aggregationOperations);

        CostMapEntity costMapEntity = findResourceViaAggregation(aggregation);

        return Optional.ofNullable(costMapEntity);
    }
}
