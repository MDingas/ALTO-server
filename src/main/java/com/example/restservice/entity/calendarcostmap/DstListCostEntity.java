package com.example.restservice.entity.calendarcostmap;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class DstListCostEntity {

    @Field("dst-node")
    private String dstNode;

    @Field("cost-values")
    private List<Integer> values;

    public DstListCostEntity(String dstNode, List<Integer> values) {
        this.dstNode = dstNode;
        this.values = values;
    }

    public String getDstNode() {
        return dstNode;
    }

    public void setDstNode(String dstNode) {
        this.dstNode = dstNode;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
