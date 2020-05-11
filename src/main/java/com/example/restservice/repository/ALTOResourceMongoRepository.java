package com.example.restservice.repository;

import com.example.restservice.entity.ALTOResourceEntity;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class ALTOResourceMongoRepository<ALTOResourceEntityType extends ALTOResourceEntity> {
    private final Class<ALTOResourceEntityType> entityClass;
    protected MongoTemplate mongoTemplate;

    public ALTOResourceMongoRepository(Class<ALTOResourceEntityType> entityClass, MongoTemplate mongoTemplate) {
        this.entityClass = entityClass;
        this.mongoTemplate = mongoTemplate;
    }

    protected List<AggregationOperation> buildGetVersionOfResourceAggregationOperations(String resourceId, String versionTag) {
        return Arrays.asList(
                match(Criteria.where("metaInfoEntity.resourceId").is(resourceId)),
                unwind("mappingEntities"),
                match(Criteria.where("mappingEntities.versionTag").is(versionTag)),
                project("metaInfoEntity")
                    .and("mappingEntities").as("mappingEntity")
        );
    }

    protected List<AggregationOperation> buildGetLatestVersionOfResourceAggregationOperations(String resourceId) {
        return Arrays.asList(
                match(Criteria.where("metaInfoEntity.resourceId").is(resourceId)),
                unwind("mappingEntities"),
                project("metaInfoEntity")
                    .and("mappingEntities").arrayElementAt(-1).as("mappingEntity")
        );
    }

    protected GroupOperation getWrapVersionInsideArrayOperation() {
        return  group()
                    .first("metaInfoEntity").as("metaInfoEntity")
                    .push("mappingEntity").as("mappingEntities");
    }

    protected GroupOperation buildWrapVersionFieldsInsideVersionArrayOperation(String... extraSubFieldsToAddToMappingObj) {
        BasicDBObject pushObj = new BasicDBObject();
        pushObj.append("versionTag", "$versionTag");

        for (String extraSubField : extraSubFieldsToAddToMappingObj) {
            String extraSubFieldRef = "$" + extraSubField;
            pushObj.append(extraSubField, extraSubFieldRef);
        }

        return group()
                .first("metaInfoEntity").as("metaInfoEntity")
                .push(pushObj).as("mappingEntities");
    }

    protected void addAggregationOperationsWithProjection(List<AggregationOperation> aggregationOperations, List<AggregationOperation> projectionOperations, String[] fieldsToProject) {
        aggregationOperations.addAll(projectionOperations);
        aggregationOperations.add(buildWrapVersionFieldsInsideVersionArrayOperation(fieldsToProject));
    }

    protected void addAggregationOperationsWithoutProjections(List<AggregationOperation> aggregationOperations) {
        aggregationOperations.add(getWrapVersionInsideArrayOperation());
    }

    protected ALTOResourceEntityType findResourceViaAggregation(TypedAggregation<ALTOResourceEntityType> aggregation) {
        AggregationResults<ALTOResourceEntityType> aggregationResults = mongoTemplate.aggregate(aggregation, entityClass);
        return aggregationResults.getUniqueMappedResult();
    }

    public Optional<ALTOResourceEntityType> findVersionOfResource(String resourceId, String versionTag) {
        Query query = new Query(
                new Criteria()
                .andOperator(
                        Criteria.where("metaInfoEntity.resourceId").is(resourceId),
                        Criteria.where("mappingEntities.versionTag").is(versionTag))
        );

        query.fields().include("metaInfoEntity");

        query.fields().include("mappingEntities").elemMatch("mappingEntities", Criteria.where("versionTag").is(versionTag));

        ALTOResourceEntityType resource = mongoTemplate.findOne(query, entityClass);

        return Optional.ofNullable(resource);
    }

    public Optional<ALTOResourceEntityType> findLatestVersionOfResource(String resourceId) {
        Query query = new Query(Criteria.where("metaInfoEntity.resourceId").is(resourceId));

        query.fields().include("metaInfoEntity");

        query.fields().include("mappingEntities").slice("mappingEntities", -1);

        ALTOResourceEntityType resource = mongoTemplate.findOne(query, entityClass);

        return Optional.ofNullable(resource);
    }

    public List<ALTOResourceEntityType> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    public void insert(ALTOResourceEntityType resource) {
        mongoTemplate.insert(resource);
    }

    public void insertAll(List<ALTOResourceEntityType> entities) {
        mongoTemplate.insertAll(entities);
    }
}
