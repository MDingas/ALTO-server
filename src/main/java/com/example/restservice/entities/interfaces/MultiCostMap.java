package com.example.restservice.entities.interfaces;

public interface MultiCostMap {
    String getCost(String metric, PID src, PID dst);
    void setCost(String metric, PID src, PID dst);
    void clear(String metric);
    void clear();
}
