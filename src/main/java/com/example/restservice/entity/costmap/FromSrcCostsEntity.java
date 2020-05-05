package com.example.restservice.entity.costmap;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class FromSrcCostsEntity {

    @Field("src-node")
    private String srcNode;

    @Field("dst-costs")
    private List<DstCostsEntity> dstCostsEntities;

    public FromSrcCostsEntity(String srcNode, List<DstCostsEntity> dstCostsEntities) {
        this.srcNode = srcNode;
        this.dstCostsEntities = dstCostsEntities;
    }

    public String getSrcNode() {
        return srcNode;
    }

    public void setSrcNode(String srcNode) {
        this.srcNode = srcNode;
    }

    public List<DstCostsEntity> getDstCostsEntities() {
        return dstCostsEntities;
    }

    public void setDstCostsEntities(List<DstCostsEntity> dstCostsEntities) {
        this.dstCostsEntities = dstCostsEntities;
    }
}
