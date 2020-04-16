package com.example.restservice.entity;

import org.springframework.data.mongodb.core.mapping.Field;

public class DstCostsEntity {

    @Field("dst-node")
    private String dstNode;

    @Field("cost-value")
    private Integer value;

    public DstCostsEntity(String dstNode, Integer value) {
        this.dstNode = dstNode;
        this.value = value;
    }

    public String getDstNode() {
        return dstNode;
    }

    public void setDstNode(String dstNode) {
        this.dstNode = dstNode;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
