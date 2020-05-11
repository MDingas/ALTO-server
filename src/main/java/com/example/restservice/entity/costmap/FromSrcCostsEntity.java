package com.example.restservice.entity.costmap;

import java.util.List;

public class FromSrcCostsEntity {

    private String srcNode;

    private List<DstCostsEntity> dstCostEntities;

    public FromSrcCostsEntity(String srcNode, List<DstCostsEntity> dstCostEntities) {
        this.srcNode = srcNode;
        this.dstCostEntities = dstCostEntities;
    }

    public String getSrcNode() {
        return srcNode;
    }

    public void setSrcNode(String srcNode) {
        this.srcNode = srcNode;
    }

    public List<DstCostsEntity> getDstCostEntities() {
        return dstCostEntities;
    }

    public void setDstCostEntities(List<DstCostsEntity> dstCostEntities) {
        this.dstCostEntities = dstCostEntities;
    }
}
