package com.example.restservice.entities;

import com.example.restservice.entities.interfaces.PID;

public class StringPID implements PID {

    private final String value;

    public StringPID(String value) {
        this.value = value;
    }
}
