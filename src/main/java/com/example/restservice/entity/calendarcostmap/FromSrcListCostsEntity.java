package com.example.restservice.entity.calendarcostmap;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class FromSrcListCostsEntity {

    @Field("src-node")
    private String srcNode;

    @Field("dst-costLists")
    private List<DstListCostEntity> dstListCostEntities;

    public FromSrcListCostsEntity(String srcNode, List<DstListCostEntity> dstListCostEntities) {
        this.srcNode = srcNode;
        this.dstListCostEntities = dstListCostEntities;
    }

    public String getSrcNode() {
        return srcNode;
    }

    public void setSrcNode(String srcNode) {
        this.srcNode = srcNode;
    }

    public List<DstListCostEntity> getDstListCostEntities() {
        return dstListCostEntities;
    }

    public void setDstListCostEntities(List<DstListCostEntity> dstListCostEntities) {
        this.dstListCostEntities = dstListCostEntities;
    }
}
