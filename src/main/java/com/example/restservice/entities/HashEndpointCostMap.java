package com.example.restservice.entities;

import com.example.restservice.entities.interfaces.CostMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import javax.ws.rs.core.MediaType;
import java.net.InetAddress;
import java.util.Set;

public class HashEndpointCostMap extends ALTOResource implements CostMap<InetAddress, String> {

    Table<InetAddress, InetAddress, String> costMap;

    public HashEndpointCostMap(MediaType mediaType, Set<MediaType> acceptTypes, String name, String tag) {
        super(mediaType, acceptTypes, name, tag);
        this.costMap = HashBasedTable.create();
    }

    @Override
    public String getCost(InetAddress src, InetAddress dst) {
        return this.costMap.get(src, dst);
    }

    @Override
    public void setCost(InetAddress src, InetAddress dst, String cost) {
        this.costMap.put(src, dst, cost);
    }

    @Override
    public void clear() {
        this.costMap.clear();
    }
}
