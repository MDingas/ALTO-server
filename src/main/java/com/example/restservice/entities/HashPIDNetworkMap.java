package com.example.restservice.entities;

import com.example.restservice.entities.interfaces.NetworkMap;
import com.example.restservice.entities.interfaces.PID;

import java.net.InetAddress;
import java.util.*;

public class HashPIDNetworkMap extends AbsALTONetworkMap implements NetworkMap<PID> {

    private final Map<PID, List<InetAddress>> aggregatedAddressesMap;

    public HashPIDNetworkMap(String name, String tag) {
        super(name, tag);
        this.aggregatedAddressesMap = new HashMap<>();
    }

    @Override
    public int size() {
        return this.aggregatedAddressesMap.size();
    }

    @Override
    public boolean isEmpty() {
        return this.aggregatedAddressesMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        return this.aggregatedAddressesMap.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return this.aggregatedAddressesMap.containsValue(o);
    }

    @Override
    public List<InetAddress> get(Object o) {
        return this.aggregatedAddressesMap.get(o);
    }

    @Override
    public List<InetAddress> put(PID pid, List<InetAddress> inetAddresses) {
        return this.aggregatedAddressesMap.put(pid, inetAddresses);
    }

    @Override
    public List<InetAddress> remove(Object o) {
        return this.aggregatedAddressesMap.remove(o);
    }

    @Override
    public void putAll(Map<? extends PID, ? extends List<InetAddress>> map) {
        this.aggregatedAddressesMap.putAll(map);
    }

    @Override
    public void clear() {
        this.aggregatedAddressesMap.clear();
    }

    @Override
    public Set<PID> keySet() {
        return this.aggregatedAddressesMap.keySet();
    }

    @Override
    public Collection<List<InetAddress>> values() {
        return this.aggregatedAddressesMap.values();
    }

    @Override
    public Set<Entry<PID, List<InetAddress>>> entrySet() {
        return this.aggregatedAddressesMap.entrySet();
    }
}
