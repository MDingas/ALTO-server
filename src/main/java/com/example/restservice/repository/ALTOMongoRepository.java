package com.example.restservice.repository;

import com.example.restservice.entity.ALTOResourceEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class ALTOMongoRepository<T extends ALTOResourceEntity> implements ALTORepository<T> {
    private final Class<T> entityClass;
    private static final String[] resourceProjectionFields = {"_id", "resource-id", "uri"};
    protected MongoTemplate mongoTemplate;

    public ALTOMongoRepository(Class<T> entityClass, MongoTemplate mongoTemplate) {
        this.entityClass = entityClass;
        this.mongoTemplate = mongoTemplate;
    }

    protected String[] getResourceProjectionFields() {
        return resourceProjectionFields;
    }

    protected List<AggregationOperation> buildVersionOfResourceAggregationOperationList(String resourceId, String version) {
        return Arrays.asList(
                match(Criteria.where("resource-id").is(resourceId)),
                unwind("mappings"),
                match(Criteria.where("mappings.version-tag").is(version))
        );
    }

    protected List<AggregationOperation> buildLatestVersionOfResourceAggregationOperationList(String resourceId) {
        return Arrays.asList(
                match(Criteria.where("resource-id").is(resourceId)),
                    project(getResourceProjectionFields())
                    .and("mappings").arrayElementAt(-1).as("mappings")
        );
    }

    @Override
    public Optional<T> findVersionOfResource(String resourceId, String versionTag) {
        Query query = new Query(
                new Criteria()
                .andOperator(
                        Criteria.where("resource-id").is(resourceId),
                        Criteria.where("mappings.version-tag").is(versionTag))
        );

        for (String projectionField : getResourceProjectionFields()) {
            query.fields().include(projectionField);
        }

        query.fields().include("mappings").elemMatch("mappings", Criteria.where("version-tag").is(versionTag));

        T resource = mongoTemplate.findOne(query, entityClass);

        return Optional.ofNullable(resource);
    }

    @Override
    public Optional<T> findAllVersionsOfResource(String resourceId) {
        Query query = new Query(Criteria.where("resource-id").is(resourceId));

        T resource = mongoTemplate.findOne(query, entityClass);

        return Optional.ofNullable(resource);
    }

    @Override
    public Optional<T> findLatestVersionOfResource(String resourceId) {
        Query query = new Query(Criteria.where("resource-id").is(resourceId));

        for (String projectionField : getResourceProjectionFields()) {
            query.fields().include(projectionField);
        }

        query.fields().include("mappings").slice("mappings", -1);

        T resource = mongoTemplate.findOne(query, entityClass);

        return Optional.ofNullable(resource);
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }

    @Override
    public void insertAll(List<T> t) {
        mongoTemplate.insertAll(t);
    }
}
