package com.example.restservice.repository.costmap;

import com.example.restservice.entity.costmap.CostMapEntity;
import com.example.restservice.repository.ALTOResourceMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class CostMapMongoRepository extends ALTOResourceMongoRepository<CostMapEntity> implements CostMapRepository {

    @Autowired
    public CostMapMongoRepository(MongoTemplate mongoTemplate) {
        super(CostMapEntity.class, mongoTemplate);
    }

    private List<AggregationOperation> buildProjectCostSrcAndDstAndSingleVersion(List<String> srcPIDs, List<String> dstPIDs, String costMode, String costMetric) {
        List<AggregationOperation> operations = new ArrayList<>();

        operations.add(
                match(new Criteria().
                        andOperator(
                                Criteria.where("mappings.cost-mode").is(costMode),
                                Criteria.where("mappings.cost-metric").is(costMetric))));

        operations.add(unwind("mappings.from-src-costs"));

        if (!srcPIDs.isEmpty()) {
            operations.add(match(Criteria.where("mappings.from-src-costs.src-node").in(srcPIDs)));
        }

        operations.add(unwind("mappings.from-src-costs.dst-costs"));

        if (!dstPIDs.isEmpty()) {
            operations.add(match(Criteria.where("mappings.from-src-costs.dst-costs.dst-node").in(dstPIDs)));
        }

        operations.add(
                group("mappings.from-src-costs.src-node")
                    .first("mappings.version-tag").as("version-tag")
                    .first("resource-id").as("resource-id")
                    .first("mappings.from-src-costs.src-node").as("src-node")
                    .first("uri").as("uri")
                    .first("mappings.cost-mode").as("cost-mode")
                    .first("mappings.cost-metric").as("cost-metric")
                    .push("mappings.from-src-costs.dst-costs").as("dst-costs")
        );

        operations.add(
                project("resource-id", "version-tag", "uri", "cost-mode", "cost-metric")
                    .andExclude("_id")
                    .and("from-src-costs").nested(Fields.fields("src-node", "dst-costs"))
        );

        operations.add(
                group("resource-id")
                    .first("resource-id").as("resource-id")
                    .first("uri").as("uri")
                    .first("cost-mode").as("cost-mode")
                    .first("cost-metric").as("cost-metric")
                    .first("version-tag").as("version-tag")
                    .push("from-src-costs").as("from-src-costs"));

        operations.add(
                project("uri")
                    .andExclude("_id")
                    .and("_id").as("resource-id")
                    .and("mappings").nested(Fields.fields("version-tag","cost-mode","cost-metric","from-src-costs"))
        );

        operations.add(
                group("resource-id")
                    .first("resource-id").as("resource-id")
                    .push("mappings").as("mappings")
        );

        return operations;
    }

    @Override
    public Optional<CostMapEntity> findVersionOfResource(String resourceId, String versionTag, List<String> srcPIDs, List<String> dstPIDs, String costMode, String costMetric) {
        try {
            List<AggregationOperation> operations = new ArrayList<>(buildVersionOfResourceAggregationOperationList(resourceId, versionTag));
            operations.addAll(buildProjectCostSrcAndDstAndSingleVersion(srcPIDs, dstPIDs, costMode, costMetric));

            Aggregation aggregation = newAggregation(operations);

            AggregationResults<CostMapEntity> aggregationResults = mongoTemplate.aggregate(aggregation, "CostMaps", CostMapEntity.class);

            List<CostMapEntity> costMapEntityList = aggregationResults.getMappedResults();

            if (costMapEntityList.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(costMapEntityList.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<CostMapEntity> findLatestVersionOfResource(String resourceId, List<String> srcPIDs, List<String> dstPIDs, String costMode, String costMetric) {
        return Optional.empty();
    }
}
