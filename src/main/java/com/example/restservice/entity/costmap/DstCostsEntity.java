package com.example.restservice.entity.costmap;


public class DstCostsEntity {

    private String dstNode;

    private Integer costValue;

    public DstCostsEntity(String dstNode, Integer costValue) {
        this.dstNode = dstNode;
        this.costValue = costValue;
    }

    public String getDstNode() {
        return dstNode;
    }

    public void setDstNode(String dstNode) {
        this.dstNode = dstNode;
    }

    public Integer getCostValue() {
        return costValue;
    }

    public void setValue(Integer costValue) {
        this.costValue = costValue;
    }
}
