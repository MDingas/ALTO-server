package com.example.restservice.entities;

import com.example.restservice.entities.interfaces.CostMap;
import com.example.restservice.entities.interfaces.PID;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class HashPIDCostMap extends AbsALTOCostMap implements CostMap<PID, String> {

    Table<PID, PID, String> costMap;

    public HashPIDCostMap(String name, String tag, CostMode mode, CostMetric metric, String description) {
        super(name, tag, mode, metric, description);
        this.costMap = HashBasedTable.create();
    }

    @Override
    public String getCost(PID src, PID dst) {
        return this.costMap.get(src, dst);
    }

    @Override
    public void setCost(PID src, PID dst, String cost) {
        this.costMap.put(src, dst, cost);
    }

    @Override
    public void clear() {
        this.costMap.clear();
    }
}
